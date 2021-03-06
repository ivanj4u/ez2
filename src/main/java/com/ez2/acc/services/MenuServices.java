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

package com.ez2.acc.services;

import com.ez2.acc.dao.MenuDao;
import com.ez2.acc.entity.EzMenu;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuServices extends AuditTrailServices {
    private static final Logger logger = LoggerFactory.getLogger(MenuServices.class);
    @Autowired
    private MenuDao daoMenu;

    public List<EzMenu> queryList(Predicate predicate) {
        List<EzMenu> list = new ArrayList<>();
        try {
            if (predicate != null) {
                list = daoMenu.findAll(predicate);
            } else {
                list = daoMenu.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public EzMenu getMenu(String menuId) {
        EzMenu menu = null;
        try {
            menu = daoMenu.findOne(menuId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return menu;
    }

    public List<EzMenu> getAllMenu() {
        List<EzMenu> list = new ArrayList<>();
        try {
            list = daoMenu.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(Object pojo) {
        EzMenu menu = (EzMenu) pojo;
        try {
            saveAudit(menu);
            daoMenu.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Object pojo) {
        EzMenu updatedMenu = (EzMenu) pojo;
        try {
            EzMenu menu = daoMenu.findOne(updatedMenu.getMenuId());
            BeanUtils.copyProperties(updatedMenu, menu);
            updateAudit(menu);
            daoMenu.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
