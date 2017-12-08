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

package com.ez2.acc.framework.listener;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.TextField;

public abstract class FieldShortcutListener {

    final ShortcutListener enterShortCut = new ShortcutListener(
            "EnterOnTextAreaShorcut", ShortcutAction.KeyCode.ENTER, null) {
        @Override
        public void handleAction(Object sender, Object target) {
            onEnterKeyPressed();
        }
    };

    public FieldShortcutListener() {
    }

    public void install(final TextField textField) {
        textField.addFocusListener((FieldEvents.FocusListener) event -> {
            textField.addShortcutListener(enterShortCut);
        });

        textField.addBlurListener((FieldEvents.BlurListener) event -> {
            textField.removeShortcutListener(enterShortCut);
        });
    }

    public abstract void onEnterKeyPressed();
}
