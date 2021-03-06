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

import com.ez2.acc.container.TreeMenuContainer;
import com.ez2.acc.entity.EzGroup;
import com.ez2.acc.entity.EzMenu;
import com.ez2.acc.entity.EzPriviledge;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.services.GroupServices;
import com.ez2.acc.services.MenuServices;
import com.ez2.acc.services.PriviledgeServices;
import com.ez2.acc.util.ValidationHelper;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@SpringComponent
@UIScope
public class DetailPriviledgeView extends AbstractDetailScreen {

    private static final Logger logger = LoggerFactory.getLogger(DetailPriviledgeView.class);
    @Autowired
    private GroupServices servicesGroup;
    @Autowired
    private PriviledgeServices servicesPriviledge;
    @Autowired
    private MenuServices servicesMenu;
    private TextField txtGroupId, txtGroupName;
    private Button btnEdit;
    private TreeGrid<TreeMenuContainer> tree;
    private Hashtable<String, TreeMenuContainer> hMenu;
    private List<EzMenu> listMenu;
    private TreeMenuContainer selectedItem;
    private Window wMenuContainerView;
    private DetailMenuContainerView menuContainerView;
    private EzGroup pojoGroup;

    @Override
    protected void doSave() {
        if (!doValidate())
            return;

        try {
            if (getMode() == Constants.APP_MODE.MODE_NEW) {
                /**
                 * Save Group
                 * Save Priviledge
                 */
                pojoGroup = new EzGroup();
                pojoGroup.setGroupId(new Long(txtGroupId.getValue()));
                pojoGroup.setGroupName(txtGroupName.getValue());
                servicesGroup.save(pojoGroup);

                savePriviledge();

                listener.onAfterAdded(pojoGroup);
            } else {
                /**
                 * Edit Group
                 * Hapus Priviledge
                 * Save Priviledge
                 */
                pojoGroup.setGroupName(txtGroupName.getValue());
                servicesGroup.update(pojoGroup);

                servicesPriviledge.deletePriviledgeGroup(pojoGroup.getGroupId());

                savePriviledge();

                listener.onAfterUpdated(pojoGroup);
            }
            doReset();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    private void savePriviledge() throws Exception {
        for (TreeMenuContainer menuContainer : hMenu.values()) {
            if (!validateMenuContainer(menuContainer))
                continue;

            EzPriviledge priviledge = new EzPriviledge();
            priviledge.setMenuId(menuContainer.getMenuId());
            priviledge.setGroupId(pojoGroup.getGroupId());
            priviledge.setIsAdd(menuContainer.getIsAdd().charAt(0));
            priviledge.setIsUpdate(menuContainer.getIsUpdate().charAt(0));
            priviledge.setIsView(menuContainer.getIsView().charAt(0));
            priviledge.setIsDelete(menuContainer.getIsDelete().charAt(0));
            servicesPriviledge.save(priviledge);
        }
    }

    private boolean validateMenuContainer(TreeMenuContainer menuContainer) {
        if (menuContainer.getIsAdd().equals("0")
                && menuContainer.getIsUpdate().equals("0")
                && menuContainer.getIsView().equals("0")
                && menuContainer.getIsDelete().equals("0")) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean doValidate() {
        if (ValidationHelper.validateRequired(txtGroupId)
                && ValidationHelper.validateRequired(txtGroupName))
            return true;

        NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MANDATORY);
        return false;
    }

    @Override
    protected void doReset() {
        txtGroupId.setValue("");
        txtGroupName.setValue("");
        selectedItem = null;
        pojoGroup = null;
    }

    @Override
    protected void setContentById(Object pojo) {
        try {
            pojoGroup = pojo != null ? (EzGroup) pojo : null;
            if (pojoGroup != null) {
                txtGroupId.setValue(String.valueOf(pojoGroup.getGroupId()));
                txtGroupName.setValue(pojoGroup.getGroupName());
                createMenuData(false);
            } else {
                NotificationHelper.showNotification(Constants.APP_MESSAGE.INFO_DATA_NOT_EXIST);
                doCancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_GET_DETAIL);
        }
    }

    @Override
    protected void beforeInitComponent() {
        hMenu = new Hashtable<>();
        listMenu = new ArrayList<>();
    }

    @Override
    protected Component initDetail() {
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName(ValoTheme.PANEL_WELL);

        GridLayout grid = new GridLayout(3, 4);
        int row = 0;

        Label lbl = new Label("Id Group");
        lbl.setWidth("110px");

        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtGroupId = new TextField(), 1, row++);

        grid.addComponent(new Label("Nama Group"), 0, row);
        grid.addComponent(txtGroupName = new TextField(), 1, row++);

        layout.addComponent(grid);
        layout.addComponent(btnEdit = new Button("Ubah"));
        btnEdit.addListener(event -> {
            if (selectedItem == null) {
                NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MUST_BE_SELECTED);
                return;
            }
            showMenuContainerWindow();
        });

        layout.setComponentAlignment(btnEdit, Alignment.MIDDLE_RIGHT);
        layout.addComponent(tree = new TreeGrid<>());

        tree.addItemClickListener(event -> {
            if (event.getItem() != null) {
                selectedItem = event.getItem();
            }
        });

        return layout;
    }

    private void showMenuContainerWindow() {
        if (selectedItem == null) {
            NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MUST_BE_SELECTED);
            return;
        }
        if (menuContainerView == null) {
            try {
                menuContainerView = new DetailMenuContainerView(result -> {
                    getUI().removeWindow(wMenuContainerView);
                    TreeMenuContainer menuContainer = (TreeMenuContainer) result;
                    hMenu.put(menuContainer.getMenuId(), menuContainer);
                    createMenuData(true);
                });
                wMenuContainerView = new Window();
                wMenuContainerView.setContent(menuContainerView);
                menuContainerView.show();
                wMenuContainerView.setModal(true);
                wMenuContainerView.setWidth("55%");
                wMenuContainerView.setHeight("45%");
                wMenuContainerView.addStyleName(ValoTheme.PANEL_WELL);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        menuContainerView.setParam(selectedItem);
        menuContainerView.unpack();
        getUI().addWindow(wMenuContainerView);
    }

    @Override
    protected void afterInitComponent() {
        createTreeData();
    }

    private void createTreeData() {
        tree.setWidth("100%");
        tree.addColumn(TreeMenuContainer::getMenuId).setCaption("Id Menu");
        tree.addColumn(TreeMenuContainer::getMenuName).setCaption("Nama Menu");
        tree.addColumn(TreeMenuContainer::getIsAdd).setCaption("Tambah");
        tree.addColumn(TreeMenuContainer::getIsUpdate).setCaption("Ubah");
        tree.addColumn(TreeMenuContainer::getIsView).setCaption("Lihat");
        tree.addColumn(TreeMenuContainer::getIsDelete).setCaption("Hapus");
    }

    @SuppressWarnings("unchecked")
    private void createMenuData(boolean isEdited) {
        TreeDataProvider<TreeMenuContainer> dataProvider = (TreeDataProvider<TreeMenuContainer>) tree.getDataProvider();
        TreeData<TreeMenuContainer> treeData = dataProvider.getTreeData();
        treeData.clear();
        if (!isEdited) {
            listMenu = servicesMenu.getAllMenu();
            hMenu.clear();
        }
        TreeMenuContainer menuContainer;
        for (EzMenu menu : listMenu) {
            if (isEdited) {
                menuContainer = hMenu.get(menu.getMenuId());
            } else {
                if (menu.getParentId().equals("0")) {
                    menuContainer = packContainer(menu, true);
                } else {
                    menuContainer = packContainer(menu, false);
                }
                hMenu.put(menu.getMenuId(), menuContainer);
            }
            treeData.addItem(menuContainer.getParentMenu(), menuContainer);
        }
        dataProvider.refreshAll();
    }

    private TreeMenuContainer packContainer(EzMenu menu, boolean isParent) {
        TreeMenuContainer menuContainer = new TreeMenuContainer();
        menuContainer.setMenuId(menu.getMenuId());
        menuContainer.setMenuName(menu.getMenuName());
        if (getMode() == Constants.APP_MODE.MODE_UPDATE || getMode() == Constants.APP_MODE.MODE_VIEW) {
            try {
                EzPriviledge priviledge = servicesPriviledge.getMenuPriviledge(menu.getMenuId(), pojoGroup.getGroupId());
                if (priviledge != null) {
                    menuContainer.setIsAdd(String.valueOf(priviledge.getIsAdd()));
                    menuContainer.setIsUpdate(String.valueOf(priviledge.getIsUpdate()));
                    menuContainer.setIsView(String.valueOf(priviledge.getIsView()));
                    menuContainer.setIsDelete(String.valueOf(priviledge.getIsDelete()));
                } else {
                    menuContainer.setIsAdd("0");
                    menuContainer.setIsUpdate("0");
                    menuContainer.setIsView("0");
                    menuContainer.setIsDelete("0");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        } else {
            menuContainer.setIsAdd("0");
            menuContainer.setIsUpdate("0");
            menuContainer.setIsView("0");
            menuContainer.setIsDelete("0");
        }
        if (isParent) {
            menuContainer.setParentMenu(null);
        } else {
            menuContainer.setParentMenu(hMenu.get(menu.getParentId()));
        }

        return menuContainer;
    }

    @Override
    public void setModeNew() {
        doReset();
        createMenuData(false);
        txtGroupId.setEnabled(true);
        btnSave.setVisible(true);
    }

    @Override
    public void setModeUpdate() {
        txtGroupId.setEnabled(false);
        btnSave.setVisible(true);
    }

    @Override
    public void setModeView() {
        txtGroupId.setEnabled(false);
        btnSave.setVisible(false);
    }
}
