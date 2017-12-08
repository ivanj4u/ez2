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

package com.ez2.acc.util;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Notification;

public class ValidationHelper {

    public static boolean validateFieldWithCaption(AbstractField field) {
        if (field.getValue() == null || field.getValue().toString().equals("")) {
            Notification.show("Mohon mengisi field : " + field.getCaption(), Notification.Type.HUMANIZED_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validateRequired(AbstractField field) {
        if (field.getValue() == null || field.getValue().toString().equals("")) {
            field.setComponentError(new UserError("Mohon mengisi field"));
            field.focus();
            return false;
        }
        return true;
    }

    public static boolean validateFieldWithoutWarn(AbstractField field) {
        if (field.getValue() == null || field.getValue().toString().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean validateValueNotNull(Object value) {
        if (value != null && !value.toString().equals(""))
            return true;
        return false;
    }
}
