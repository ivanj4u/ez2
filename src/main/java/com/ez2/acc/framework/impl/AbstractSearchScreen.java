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

package com.ez2.acc.framework.impl;

import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.listener.DetailCallbackListener;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
public abstract class AbstractSearchScreen extends AbstractScreen implements DetailCallbackListener {
    protected DetailCallbackListener listener;
    protected GridLayout grid;
    protected Button btnAdd, btnEdit, btnDelete, btnView, btnSearch, btnReset, btnDetail;
    protected int row = 0;
    protected Window windowDetail = null;
    protected GridLayout totalDetail = null;
    protected HorizontalLayout buttonDetail;
    private Object rowId;

    public AbstractSearchScreen(DetailCallbackListener listener) {
        this.listener = listener;
    }

    public AbstractSearchScreen() {
    }

    @Override
    protected void initComponents() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addStyleName(ValoTheme.PANEL_WELL);

        layout.addComponent(initPencarian());
        layout.addComponent(initResult());
        layout.setExpandRatio(layout.getComponent(0), 0.5f);
        layout.setExpandRatio(layout.getComponent(1), 1.0f);

        setContent(layout);
        Responsive.makeResponsive(this);
    }

    private Panel initPencarian() {
        Panel panel = new Panel("Kriteria Pencarian");
        panel.addStyleName(ValoTheme.PANEL_WELL);
        panel.setSizeFull();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        grid = new GridLayout(getGridColumn(), getGridRow());
        grid.setSpacing(false);

        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponent(btnSearch = new Button("Cari"));
        buttonBar.addComponent(btnReset = new Button("Reset"));

        btnSearch.addListener(event -> {
            if (!validateSearchRequired()) {
                NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MANDATORY);
                return;
            }
            if (!validateValue()) {
                return;
            }
            doSearch();
        });

        btnReset.addListener(event -> {
            doReset();
        });

        initGridComponent();
        grid.addComponent(buttonBar, 1, row++);
        grid.setComponentAlignment(buttonBar, Alignment.MIDDLE_LEFT);

        layout.addComponent(grid);
        panel.setContent(layout);

        return panel;
    }

    protected Panel initResult() {
        Panel panel = new Panel("Hasil Pencarian");
        panel.addStyleName(ValoTheme.PANEL_WELL);
        panel.setSizeFull();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        initTableData();
        initButtonDetailScreen();

        layout.addComponent(buttonDetail);
        layout.addComponent(getTableData());
        layout.setComponentAlignment(buttonDetail, Alignment.MIDDLE_RIGHT);
        layout.setExpandRatio(layout.getComponent(1), 1.0f);

        initTotalDetail();
        if (totalDetail != null)
            layout.addComponent(totalDetail);

        panel.setContent(layout);
        return panel;
    }

    protected Grid<?> initTable() {
        Grid<?> table = new Grid<>();
        table.setSizeFull();
        table.addStyleName(ValoTheme.TABLE_BORDERLESS);
        table.setSelectionMode(Grid.SelectionMode.SINGLE);
        table.addItemClickListener(event -> setRowId(event.getItem()));

        return table;
    }

    protected void initTotalDetail() {

    }

    protected void initButtonDetailScreen() {
        buttonDetail = new HorizontalLayout();
        buttonDetail.setSpacing(false);
        buttonDetail.addComponent(btnAdd = new Button("Tambah"));
        buttonDetail.addComponent(btnEdit = new Button("Ubah"));
        buttonDetail.addComponent(btnView = new Button("Lihat"));
        buttonDetail.addComponent(btnDelete = new Button("Hapus"));
        buttonDetail.addComponent(btnDetail = new Button("Pilih"));
        btnDetail.setVisible(false);

        btnAdd.setVisible(this.isAuthorizedToAdd());
        btnEdit.setVisible(this.isAuthorizedToUpdate());
        btnView.setVisible(this.isAuthorizedToView());
        btnDelete.setVisible(this.isAuthorizedToDelete());

        btnAdd.addListener(event -> {
            doAdd();
        });

        btnEdit.addListener(event -> {
            doEdit();
        });

        btnView.addListener(event -> {
            doView();
        });

        btnDelete.addListener(event -> {
            doDelete();
        });

        btnDetail.addListener(event -> {
            doDetail();
        });

    }

    protected void showDetailScreen() {
        if (windowDetail == null) {
            windowDetail = new Window(getDetailScreenTitle());
            windowDetail.setContent(getDetailScreen());
            getDetailScreen().setSizeFull();
            windowDetail.setModal(getModality());
            windowDetail.setWidth(getDetailScreenWidth());
            windowDetail.setHeight(getDetailScreenHeight());
        }
        getUI().addWindow(windowDetail);
        windowDetail.center();
    }

    protected boolean getModality() {
        return true;
    }

    protected void doAdd() {
        getDetailScreen().setMode(Constants.APP_MODE.MODE_NEW);
        showDetailScreen();
    }

    protected void doEdit() {
        if (getRowId() == null) {
            NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MUST_BE_SELECTED);
            return;
        }
        getDetailScreen().setMode(Constants.APP_MODE.MODE_UPDATE);
        showDetailScreen();
        getDetailScreen().setContentById(getRowId());
    }

    protected void doView() {
        if (getRowId() == null) {
            NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MUST_BE_SELECTED);
            return;
        }
        getDetailScreen().setMode(Constants.APP_MODE.MODE_VIEW);
        showDetailScreen();
        getDetailScreen().setContentById(getRowId());
    }

    protected void doDelete() {
    }

    protected void doDetail() {
    }

    protected boolean validateValue() {
        return true;
    }

    @Override
    public void setModeNew() {

    }

    @Override
    public void setModeUpdate() {

    }

    @Override
    public void setModeView() {

    }

    @Override
    public void onAfterAdded(Object pojo) {
        getUI().removeWindow(windowDetail);
        NotificationHelper.showNotification(Constants.APP_MESSAGE.INFO_DATA_SAVED);
    }

    @Override
    public void onAfterUpdated(Object pojo) {
        getUI().removeWindow(windowDetail);
        NotificationHelper.showNotification(Constants.APP_MESSAGE.INFO_DATA_UPDATED);
    }

    @Override
    public void onAfterViewed() {
        onCancel();
    }

    @Override
    public void onCancel() {
        getUI().removeWindow(windowDetail);
        setRowId(null);
    }

    public Object getRowId() {
        return rowId;
    }

    public void setRowId(Object rowId) {
        this.rowId = rowId;
    }

    protected abstract int getGridColumn();

    protected abstract int getGridRow();

    protected abstract void initGridComponent();

    protected abstract Component getTableData();

    protected abstract void initTableData();

    protected abstract AbstractDetailScreen getDetailScreen();

    protected abstract String getDetailScreenTitle();

    protected abstract String getDetailScreenWidth();

    protected abstract String getDetailScreenHeight();

    protected abstract void doSearch();

    protected abstract void doReset();

    protected abstract boolean validateSearchRequired();
}
