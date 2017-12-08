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

package com.ez2.acc.view.param;

import com.ez2.acc.entity.EzParam;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.framework.impl.AbstractSearchScreen;
import com.ez2.acc.services.ParamServices;
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
public class ListParamView extends AbstractSearchScreen implements View {

    private static final Logger logger = LoggerFactory.getLogger(ListParamView.class);
    private final String KEY_ = "Id Param";
    private final String VALUE_ = "Nilai Param";
    private final String DESC_ = "Deskripsi";
    @Autowired
    private ParamServices servicesParam;
    @Autowired
    private ApplicationContext applicationContext;
    private TextField txtKey;
    private AbstractDetailScreen detailScreen;
    private Grid<EzParam> table;
    private List<EzParam> list;

    @Override
    protected int getGridColumn() {
        return 2;
    }

    @Override
    protected int getGridRow() {
        return 2;
    }

    @Override
    protected void initGridComponent() {
        Label lbl = new Label("Id Param");
        lbl.setWidth("110px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtKey = new TextField(), 1, row++);
    }

    @Override
    protected Component getTableData() {
        return table;
    }

    @Override
    protected void initTableData() {
        list = new ArrayList<>();
        table = (Grid<EzParam>) initTable();
        table.addColumn(EzParam::getKey).setCaption(KEY_);
        table.addColumn(EzParam::getValue).setCaption(VALUE_);
        table.addColumn(EzParam::getDescription).setCaption(DESC_);
    }

    @Override
    protected AbstractDetailScreen getDetailScreen() {
        if (detailScreen == null) {
            try {
                detailScreen = applicationContext.getBean(DetailParamView.class);
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
        return "Pengaturan Sys Param";
    }

    @Override
    protected String getDetailScreenWidth() {
        return "45%";
    }

    @Override
    protected String getDetailScreenHeight() {
        return "60%";
    }

    @Override
    protected void doSearch() {
        try {
            list = servicesParam.queryList(txtKey.getValue());
            table.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_SEARCH);
        }
    }

    @Override
    protected void doReset() {
        txtKey.setValue("");

        list.clear();
        table.setItems(list);
    }

    @Override
    protected boolean validateSearchRequired() {
        return true;
    }
}
