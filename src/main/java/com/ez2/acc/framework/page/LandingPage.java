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

package com.ez2.acc.framework.page;

import com.ez2.acc.framework.impl.AbstractScreen;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView
public class LandingPage extends AbstractScreen implements View {

    @Override
    protected void initComponents() {
        setContent(initLanding());
    }

    private Component initLanding() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Resource res = new ThemeResource("img/landing.gif");
        Image img = new Image(null, res);
        img.setSizeFull();
        layout.addComponent(img);
        layout.setComponentAlignment(img, Alignment.MIDDLE_CENTER);

        return layout;
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
}