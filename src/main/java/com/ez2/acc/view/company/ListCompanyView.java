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

package com.ez2.acc.view.company;

import com.ez2.acc.entity.EzCompany;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.framework.impl.AbstractSearchScreen;
import com.ez2.acc.services.CompanyServices;
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
public class ListCompanyView extends AbstractSearchScreen implements View {
    private static final Logger logger = LoggerFactory.getLogger(ListCompanyView.class);
    private final String COMPANY_CODE = "Id Perusahaan";
    private final String COMPANY_NAME = "Nama Perusahaan";
    private final String TELP = "No Telp";
    private final String EMAIL = "Email";
    private final String FAX = "Fax";
    private final String PARENT_ID = "Id Parent";
    @Autowired
    private CompanyServices servicesCompany;
    @Autowired
    private ApplicationContext applicationContext;
    private TextField txtCompanyCode, txtName;
    private AbstractDetailScreen detailScreen;
    private List<EzCompany> list;
    private Grid<EzCompany> table;

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
        Label lbl = new Label("Id Perusahaan");
        lbl.setWidth("145px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtCompanyCode = new TextField(), 1, row++);
        grid.addComponent(new Label("Nama Perusahaan"), 0, row);
        grid.addComponent(txtName = new TextField(), 1, row++);
    }

    @Override
    protected Component getTableData() {
        return table;
    }

    @Override
    protected void initTableData() {
        list = new ArrayList<>();
        table = (Grid<EzCompany>) initTable();
        table.addColumn(EzCompany::getCompanyCode).setCaption(COMPANY_CODE);
        table.addColumn(EzCompany::getName).setCaption(COMPANY_NAME);
        table.addColumn(EzCompany::getPhone).setCaption(TELP);
        table.addColumn(EzCompany::getEmail).setCaption(EMAIL);
        table.addColumn(EzCompany::getFax).setCaption(FAX);
        table.addColumn(EzCompany::getParentCode).setCaption(PARENT_ID);
    }

    @Override
    protected AbstractDetailScreen getDetailScreen() {
        if (detailScreen == null) {
            try {
                detailScreen = applicationContext.getBean(DetailCompanyView.class);
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
        return "Pendaftaran Perusahaan";
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
            list = servicesCompany.queryList(txtCompanyCode.getValue(), txtName.getValue());
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
            list.add((EzCompany) pojo);
            table.setItems(list);
        }
    }

    @Override
    public void onAfterUpdated(Object pojo) {
        super.onAfterUpdated(pojo);
        if (pojo != null) {
            EzCompany companyBaru = (EzCompany) pojo;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCompanyCode().equals(companyBaru.getCompanyCode())) {
                    list.remove(i);
                    list.add(companyBaru);
                    break;
                }
            }
            table.setItems(list);
        }
    }

    @Override
    protected void doReset() {
        txtCompanyCode.setValue("");
        txtName.setValue("");
        list.clear();
        table.setItems(list);
    }

    @Override
    protected boolean validateSearchRequired() {
        return true;
    }

}
