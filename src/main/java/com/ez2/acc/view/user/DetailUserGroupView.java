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
import com.ez2.acc.entity.EzGroup;
import com.ez2.acc.entity.EzUser;
import com.ez2.acc.entity.EzUserGroup;
import com.ez2.acc.framework.component.ItemComponent;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.framework.listener.FieldShortcutListener;
import com.ez2.acc.services.GroupServices;
import com.ez2.acc.services.UserGroupServices;
import com.ez2.acc.services.UserServices;
import com.ez2.acc.util.ValidationHelper;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

@SpringComponent
@UIScope
public class DetailUserGroupView extends AbstractDetailScreen {
    private static final Logger logger = LoggerFactory.getLogger(DetailUserGroupView.class);
    @Autowired
    private UserGroupServices servicesUserGroup;
    @Autowired
    private UserServices servicesUser;
    @Autowired
    private GroupServices servicesGroup;
    private TextField txtUserId, txtName;
    private List<EzUserGroup> listUserGroup;
    private Hashtable<Object, ItemComponent> hItem;
    private TwinColSelect<ItemComponent> selectGroup;
    private EzUser pojoUser;

    @Override
    protected Component initDetail() {
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName(ValoTheme.PANEL_WELL);

        GridLayout grid = new GridLayout(3, 4);
        int row = 0;

        Label lbl = new Label("Id Pengguna");
        lbl.setWidth("155px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtUserId = new TextField(), 1, row++);
        FieldShortcutListener listener = new FieldShortcutListener() {
            @Override
            public void onEnterKeyPressed() {
                if (ValidationHelper.validateFieldWithoutWarn(txtUserId)) {
                    doSearchUser(txtUserId.getValue());
                }
            }
        };
        listener.install(txtUserId);

        txtUserId.addValueChangeListener(event -> {
            doResetUser();
        });

        grid.addComponent(new Label("Nama Pengguna"), 0, row);
        grid.addComponent(txtName = new TextField(), 1, row++);
        txtName.setEnabled(false);

        grid.addComponent(new Label("Group"), 0, row);
        grid.addComponent(selectGroup = new TwinColSelect<>(), 1, row++);
        createSelectGroupData();

        layout.addComponent(grid);

        return layout;
    }

    private void createSelectGroupData() {
        hItem = new Hashtable<>();
        try {
            List<EzGroup> groups = servicesGroup.getAllGroup();
            if (groups != null && groups.size() > 0) {
                for (EzGroup group : groups
                        ) {
                    hItem.put(group.getGroupId(), new ItemComponent(group.getGroupId(), group.getGroupName()));
                }
            }
            selectGroup.setItems(hItem.values());
            selectGroup.setItemCaptionGenerator(ItemComponent::getCaption);
            selectGroup.setRows(hItem.size() + 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    private void doSearchUser(String userId) {
        try {
            pojoUser = servicesUser.getUser(userId);
            if (pojoUser != null) {
                txtName.setValue(pojoUser.getName());
            } else {
                txtName.setValue("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void setModeNew() {
        doReset();
        txtUserId.setEnabled(true);
        selectGroup.setEnabled(true);

        btnSave.setVisible(true);
    }

    @Override
    public void setModeUpdate() {
        txtUserId.setEnabled(false);
        selectGroup.setEnabled(true);

        btnSave.setVisible(true);
    }

    @Override
    public void setModeView() {
        txtUserId.setEnabled(false);
        selectGroup.setEnabled(false);

        btnSave.setVisible(false);
    }

    @Override
    protected void doSave() {
        if (!doValidate() || !doValidateValue()) {
            return;
        }
        try {
            Set<ItemComponent> set = selectGroup.getSelectedItems();
            if (getMode() == Constants.APP_MODE.MODE_UPDATE) {
                // Hapus dulu userGroup yang ada
                for (EzUserGroup group : listUserGroup) {
                    servicesUserGroup.delete(group);
                }
            }

            for (ItemComponent itemComponent : set) {
                EzUserGroup userGroup = new EzUserGroup();
                userGroup.setGroupId((Long) itemComponent.getValue());
                userGroup.setUserId(txtUserId.getValue());
                servicesUserGroup.save(userGroup);
            }
            if (getMode() == Constants.APP_MODE.MODE_NEW)
                listener.onAfterUpdated(null);
            else
                listener.onAfterAdded(null);

            doReset();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_NOT_SAVE);
        }
    }

    @Override
    protected boolean doValidate() {
        if (ValidationHelper.validateRequired(txtUserId) && ValidationHelper.validateRequired(txtName))
            return true;

        NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MANDATORY);
        return false;
    }

    private boolean doValidateValue() {
        if (selectGroup.getSelectedItems().size() == 0) {
            NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MANDATORY);
            return false;
        }
        return true;
    }

    @Override
    protected void doReset() {
        txtUserId.setValue("");
        selectGroup.deselectAll();
        doResetUser();
    }

    private void doResetUser() {
        txtName.setValue("");
        pojoUser = null;
    }

    @Override
    protected void setContentById(Object pojo) {
        selectGroup.deselectAll();
        try {
            JoinUserGroup join = pojo != null ? ((JoinUserGroup) pojo) : null;
            if (join != null) {
                listUserGroup = servicesUserGroup.getUserGroupByUser(join.getUserGroup_userId());
                txtUserId.setValue(join.getUserGroup_userId());
                doSearchUser(join.getUserGroup_userId());
                for (EzUserGroup userGroup : listUserGroup
                        ) {
                    selectGroup.select(hItem.get(userGroup.getGroupId()));
                }
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
}
