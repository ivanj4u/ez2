/*
 * This file is part of the EZ2 Accounting Project.
 * Author :
 * - Ivan Aribanilia
 * - Lukman Arizal
 * - Aristion Rizki
 * - Gabriel Sebastian
 *
 * Copyright (c) 2017. EZ2 Company.
 * Inc. All Rights Reserved. Terms of Use, Privacy and Trademark Guidelines
 */

package com.ez2.acc.loader;

import com.ez2.acc.entity.EzMenu;
import com.ez2.acc.entity.EzPriviledge;
import com.ez2.acc.entity.EzUser;
import com.ez2.acc.entity.EzUserGroup;
import com.ez2.acc.framework.impl.AbstractScreen;
import com.ez2.acc.services.MenuServices;
import com.ez2.acc.services.PriviledgeServices;
import com.ez2.acc.services.UserGroupServices;
import com.vaadin.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

@SpringComponent
public class MenuLoader {
    private static final Logger logger = LoggerFactory.getLogger(MenuLoader.class);
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private UserGroupServices servicesUserGroup;
    @Autowired
    private PriviledgeServices servicesPriviledge;
    @Autowired
    private MenuServices servicesMenu;
    private Vector<EzMenu> v = new Vector<>();
    private Vector<EzMenu> vSessionedPerUser = new Vector<>();
    private Hashtable<String, EzPriviledge> hSessionedMenuperUser = new Hashtable<>();
    private Hashtable<String, EzPriviledge> hSessionedMenuperUser2 = new Hashtable<>();

    @PostConstruct
    public void load() {
        List<EzMenu> list = servicesMenu.getAllMenu();
        v.clear();
        v.addAll(list);
    }

    @SuppressWarnings("unchecked")
    public AbstractScreen getScreen(String menuId) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (EzMenu menu : v) {
            if (menu.getMenuId().equals(menuId)) {
                Class c = Class.forName(menu.getMenuClass());
                AbstractScreen obj = (AbstractScreen) applicationContext.getBean(c);
                obj.setParam(menu.getParam());
                return obj;
            }
        }
        return null;
    }

    public EzPriviledge getPriviledge(String screenClassName) {
        return hSessionedMenuperUser2.get(screenClassName);
    }

    public Vector<EzMenu> getAuthorizedMenu() {
        return vSessionedPerUser;
    }

    public void setAuthorizedMenu(EzUser user) {
        hSessionedMenuperUser.clear();
        vSessionedPerUser.removeAllElements();
        vSessionedPerUser.addAll(v);
        Vector<EzMenu> vTemp = new Vector<>();
        try {

            List<EzUserGroup> userGroups = servicesUserGroup.getUserGroupByUsername(user.getUsername());
            for (EzUserGroup userGroup : userGroups) {
                List<EzPriviledge> priviledges = servicesPriviledge.getGroupPriviledge(String.valueOf(userGroup.getGroupId()));
                for (EzPriviledge p : priviledges) {
                    EzPriviledge privPrev = hSessionedMenuperUser.get(p.getMenuId());
                    if (privPrev != null) {
                        if (privPrev.getIsAdd() == '0')
                            privPrev.setIsAdd(p.getIsAdd());
                        if (privPrev.getIsDelete() == '0')
                            privPrev.setIsDelete(p.getIsDelete());
                        if (privPrev.getIsUpdate() == '0')
                            privPrev.setIsUpdate(p.getIsUpdate());
                        if (privPrev.getIsView() == '0')
                            privPrev.setIsView(p.getIsView());
                    } else {
                        hSessionedMenuperUser.put(p.getMenuId(), p);
                        EzMenu menu = servicesMenu.getMenu(p.getMenuId());
                        if (menu != null && menu.getMenuClass() != null && !menu.getMenuClass().equals("")) {
                            hSessionedMenuperUser2.put(menu.getMenuClass(), p);
                        }
                        vTemp.add(menu);
                    }
                }
            }
            for (EzMenu menu : v) {
                boolean isShowed = false;
                for (EzMenu menu_ : vTemp) {
                    if (menu_.getMenuId().equals(menu.getMenuId())) {
                        isShowed = true;
                        break;
                    }
                }
                if (!isShowed) {
                    vSessionedPerUser.remove(menu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public void clear() {
        hSessionedMenuperUser.clear();
        hSessionedMenuperUser2.clear();
        vSessionedPerUser.removeAllElements();
    }
}
