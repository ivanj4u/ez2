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

import com.ez2.acc.entity.EzUser;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.criteria.PredicatesBuilder;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.framework.impl.AbstractSearchScreen;
import com.ez2.acc.services.UserServices;
import com.ez2.acc.util.ValidationHelper;
import com.querydsl.core.types.dsl.PathBuilder;
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
public class ListUserView extends AbstractSearchScreen implements View {
    private static final Logger logger = LoggerFactory.getLogger(ListUserView.class);
    private final String USER_ID = "Id Pengguna";
    private final String NAME = "Nama";
    private final String COMPANY_ID = "Id Perusahaan";
    private final String TELP = "No Telp";
    @Autowired
    private UserServices servicesUser;
    @Autowired
    private ApplicationContext applicationContext;
    private TextField txtUserId, txtName;
    private AbstractDetailScreen detailScreen;
    private List<EzUser> list;
    private Grid<EzUser> table;

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
        Label lbl = new Label("Id Pengguna");
        lbl.setWidth("100px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtUserId = new TextField(), 1, row++);
        grid.addComponent(new Label("Nama"), 0, row);
        grid.addComponent(txtName = new TextField(), 1, row++);
    }

    @Override
    protected Component getTableData() {
        return table;
    }

    @Override
    protected void initTableData() {
        list = new ArrayList<>();
        table = (Grid<EzUser>) initTable();
        table.addColumn(EzUser::getUserId).setCaption(USER_ID);
        table.addColumn(EzUser::getCompanyCode).setCaption(COMPANY_ID);
        table.addColumn(EzUser::getName).setCaption(NAME);
        table.addColumn(EzUser::getPhone).setCaption(TELP);
    }

    @Override
    protected AbstractDetailScreen getDetailScreen() {
        if (detailScreen == null) {
            try {
                detailScreen = applicationContext.getBean(DetailUserView.class);
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
        return "Pendaftaran Pengguna";
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
            PredicatesBuilder builder = new PredicatesBuilder();
            if (ValidationHelper.validateValueNotNull(txtUserId.getValue())) {
                builder.add("userId", ":", txtUserId.getValue());
            }
            if (ValidationHelper.validateValueNotNull(txtName.getValue())) {
                builder.add("name", "%", txtName.getValue());
            }
            PathBuilder<EzUser> entityPath = new PathBuilder<>(EzUser.class, "ezUser");
            list = servicesUser.queryList(builder.build(entityPath));
            table.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_SEARCH);
        }
    }

    @Override
    public void onAfterAdded(Object pojo) {
        super.onAfterAdded(pojo);
        if (pojo != null) {
            list.add((EzUser) pojo);
            table.setItems(list);
        }
    }

    @Override
    public void onAfterUpdated(Object pojo) {
        super.onAfterUpdated(pojo);
        if (pojo != null) {
            EzUser userBaru = (EzUser) pojo;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUserId().equals(userBaru.getUserId())) {
                    list.remove(i);
                    list.add(userBaru);
                    break;
                }
            }
            table.setItems(list);
        }
    }

    @Override
    protected void doReset() {
        txtName.setValue("");
        txtUserId.setValue("");
        list.clear();
        table.setItems(list);
    }

    @Override
    protected boolean validateSearchRequired() {
        return true;
    }

}
