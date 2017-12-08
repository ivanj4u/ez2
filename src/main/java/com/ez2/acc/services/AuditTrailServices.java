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

import com.ez2.acc.entity.AuditTrail;
import com.ez2.acc.entity.EzUser;
import com.ez2.acc.framework.constants.Constants;
import com.vaadin.server.VaadinSession;

import java.util.Date;

public abstract class AuditTrailServices {

    protected void saveAudit(Object pojo) throws Exception {
        EzUser user = VaadinSession.getCurrent().getAttribute(EzUser.class);
        Date now = new Date();
        if (user != null) {
            ((AuditTrail) pojo).setCreateBy(user.getUsername());
        } else {
            ((AuditTrail) pojo).setCreateBy(Constants.APP_USER.SYS_USER);
        }
        ((AuditTrail) pojo).setCreateDate(now);
        ((AuditTrail) pojo).setVersi(now.getTime());
    }

    protected void updateAudit(Object pojo) throws Exception {
        EzUser user = VaadinSession.getCurrent().getAttribute(EzUser.class);
        Date now = new Date();
        if (user != null) {
            ((AuditTrail) pojo).setUpdateBy(user.getUsername());
        } else {
            ((AuditTrail) pojo).setUpdateBy(Constants.APP_USER.SYS_USER);
        }
        ((AuditTrail) pojo).setUpdateDate(now);
        ((AuditTrail) pojo).setVersi(now.getTime());
    }

    public abstract void save(Object pojo) throws Exception;

    public abstract void update(Object pojo) throws Exception;
}
