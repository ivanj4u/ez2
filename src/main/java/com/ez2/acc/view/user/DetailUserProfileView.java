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
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.services.UserServices;
import com.ez2.acc.util.ValidationHelper;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class DetailUserProfileView extends AbstractDetailScreen {
    @Autowired
    private UserServices servicesUser;

    private TextField txtName, txtEmail, txtPhone;

    private Logger logger = LoggerFactory.getLogger(DetailUserProfileView.class);

    @Override
    protected void doSave() {
        if (!doValidate())
            return;
        try {
            EzUser user = VaadinSession.getCurrent().getAttribute(EzUser.class);
            user.setName(txtName.getValue());
            user.setEmail(txtEmail.getValue());
            user.setPhone(txtPhone.getValue());
            servicesUser.update(user);

            NotificationHelper.showNotification("Profile berhasil diubah");
            listener.onAfterUpdated(false);

            // Replace User Session
            VaadinSession.getCurrent().setAttribute(EzUser.class, user);
            doReset();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doCancel() {
        listener.onAfterUpdated(false);
    }

    @Override
    protected boolean doValidate() {
        if (ValidationHelper.validateRequired(txtName)
                && ValidationHelper.validateRequired(txtEmail)
                && ValidationHelper.validateRequired(txtPhone))
            return true;

        NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MANDATORY);
        return false;
    }

    @Override
    protected void doReset() {
        txtName.setValue("");
        txtEmail.setValue("");
        txtPhone.setValue("");
    }

    @Override
    protected void setContentById(Object pojo) {

    }

    @Override
    protected Component initDetail() {
        VerticalLayout layout = new VerticalLayout();
        GridLayout grid = new GridLayout(2, 4);
        int row = 0;
        Label lbl = new Label("Nama Pengguna");
        lbl.setWidth("155px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtName = new TextField(), 1, row++);

        grid.addComponent(new Label("Email"), 0, row);
        grid.addComponent(txtEmail = new TextField(), 1, row++);

        grid.addComponent(new Label("Phone"), 0, row);
        grid.addComponent(txtPhone = new TextField(), 1, row++);

        layout.addComponent(grid);
        return layout;
    }

    @Override
    public void setModeNew() {
        EzUser user = VaadinSession.getCurrent().getAttribute(EzUser.class);
        txtName.setValue(user.getName());
        txtEmail.setValue(user.getEmail());
        txtPhone.setValue(user.getPhone());
    }

    @Override
    public void setModeUpdate() {

    }

    @Override
    public void setModeView() {

    }

    @Override
    public boolean isAuthorizedToUpdate() {
        return true;
    }

    @Override
    public boolean isAuthorizedToView() {
        return true;
    }

    @Override
    public boolean isAuthorizedToDelete() {
        return true;
    }
}
