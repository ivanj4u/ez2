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

package com.ez2.acc.view.menu;

import com.ez2.acc.entity.EzMenu;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.framework.impl.AbstractSearchScreen;
import com.ez2.acc.services.MenuServices;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringView
public class ListMenuView extends AbstractSearchScreen implements View {
    private static final Logger logger = LoggerFactory.getLogger(ListMenuView.class);
    private final String MENU_ID = "Id Menu";
    private final String MENU_NAME = "Nama Menu";
    private final String MENU_CLASS = "Class Menu";
    private final String MENU_PARENT = "Parent Menu";
    @Autowired
    private MenuServices servicesMenu;
    @Autowired
    private ApplicationContext applicationContext;
    private TextField txtMenuId, txtMenuName, txtMenuParent;
    private AbstractDetailScreen detailScreen;
    private List<EzMenu> list;
    private Grid<EzMenu> table;

    @Override
    protected int getGridColumn() {
        return 2;
    }

    @Override
    protected int getGridRow() {
        return 4;
    }

    @Override
    protected void initGridComponent() {
        Label lbl = new Label("Id Menu");
        lbl.setWidth("115px");

        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtMenuId = new TextField(), 1, row++);

        grid.addComponent(new Label("Nama Menu"), 0, row);
        grid.addComponent(txtMenuName = new TextField(), 1, row++);

        grid.addComponent(new Label("Parent Menu"), 0, row);
        grid.addComponent(txtMenuParent = new TextField(), 1, row++);
    }

    @Override
    protected Component getTableData() {
        return table;
    }

    @Override
    protected void initTableData() {
        list = new ArrayList<>();
        table = (Grid<EzMenu>) initTable();
        table.addColumn(EzMenu::getMenuId).setCaption(MENU_ID);
        table.addColumn(EzMenu::getMenuName).setCaption(MENU_NAME);
        table.addColumn(EzMenu::getMenuClass).setCaption(MENU_CLASS);
        table.addColumn(EzMenu::getParentId).setCaption(MENU_PARENT);
    }

    @Override
    protected AbstractDetailScreen getDetailScreen() {
        if (detailScreen == null) {
            try {
                detailScreen = applicationContext.getBean(DetailMenuView.class);
                detailScreen.setListener(this);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_GET_DETAIL);
            }
        }
        return detailScreen;
    }

    @Override
    protected String getDetailScreenTitle() {
        return "Pengaturan Menu";
    }

    @Override
    protected String getDetailScreenWidth() {
        return "60%";
    }

    @Override
    protected String getDetailScreenHeight() {
        return "80%";
    }

    @Override
    protected void doSearch() {
        try {
            list = servicesMenu.queryList(txtMenuId.getValue(), txtMenuName.getValue(), txtMenuParent.getValue());
            table.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_SEARCH);
        }
    }

    @Override
    protected void doReset() {
        txtMenuId.setValue("");
        txtMenuName.setValue("");
        txtMenuParent.setValue("");
        list.clear();
        table.setItems(list);

    }

    @Override
    protected boolean validateSearchRequired() {
        return true;
    }
}
