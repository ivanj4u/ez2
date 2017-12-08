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

package com.ez2.acc.framework.component;

import com.ez2.acc.framework.constants.Constants;
import com.vaadin.ui.Notification;

public class NotificationHelper {

    public static void showNotification(String msg) {
        Notification.show(msg);
    }

    public static void showNotification(String msg, Notification.Type type) {
        Notification.show(msg, type);
    }

    public static void showNotification(Constants.APP_MESSAGE appMessage) {
        Notification.show(appMessage.getCaption(), appMessage.getMessage(), appMessage.getType());
    }

}
