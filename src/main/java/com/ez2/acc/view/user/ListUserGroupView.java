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

package com.ez2.acc.view.user;

import com.ez2.acc.container.JoinUserGroup;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.framework.impl.AbstractSearchScreen;
import com.ez2.acc.services.UserGroupServices;
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
public class ListUserGroupView extends AbstractSearchScreen implements View {
    private static final Logger logger = LoggerFactory.getLogger(ListUserGroupView.class);
    private final String GROUP_ID = "Id Group";
    private final String GROUP_NAME = "Nama Group";
    private final String USERNAME = "Id Pengguna";
    private final String NAME = "Nama Pengguna";
    @Autowired
    private UserGroupServices servicesUserGroup;
    @Autowired
    private ApplicationContext applicationContext;
    private TextField txtGroupId, txtUsername;
    private AbstractDetailScreen detailScreen;
    private List<JoinUserGroup> list;
    private Grid<JoinUserGroup> table;

    @Override
    protected int getGridColumn() {
        return 2;
    }

    @Override
    protected int getGridRow() {
        return 3;
    }

    @Override
    protected void initGridComponent() {
        Label lbl = new Label("Id Group");
        lbl.setWidth("100px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtGroupId = new TextField(), 1, row++);
        grid.addComponent(new Label("Id Pengguna"), 0, row);
        grid.addComponent(txtUsername = new TextField(), 1, row++);
    }

    @Override
    protected Component getTableData() {
        return table;
    }

    @Override
    protected void initTableData() {
        list = new ArrayList<>();
        table = (Grid<JoinUserGroup>) initTable();
        table.addColumn(JoinUserGroup::getGroup_groupId).setCaption(GROUP_ID);
        table.addColumn(JoinUserGroup::getGroup_groupName).setCaption(GROUP_NAME);
        table.addColumn(JoinUserGroup::getUser_username).setCaption(USERNAME);
        table.addColumn(JoinUserGroup::getUser_name).setCaption(NAME);
    }

    @Override
    protected AbstractDetailScreen getDetailScreen() {
        if (detailScreen == null) {
            try {
                detailScreen = applicationContext.getBean(DetailUserGroupView.class);
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
        return "Parameter User Group";
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
            list = servicesUserGroup.queryList(txtGroupId.getValue(), txtUsername.getValue());
            table.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_SEARCH);
        }
    }

    @Override
    protected void doReset() {
        txtGroupId.setValue("");
        txtUsername.setValue("");
        list.clear();
        table.setItems(list);
    }

    @Override
    protected boolean validateSearchRequired() {
        return true;
    }

}
