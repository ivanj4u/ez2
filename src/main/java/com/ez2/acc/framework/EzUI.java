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

package com.ez2.acc.framework;

import com.ez2.acc.entity.EzUser;
import com.ez2.acc.framework.page.LoginPage;
import com.ez2.acc.framework.page.MainPage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI
@Theme(ValoTheme.THEME_NAME)
@Title("Vaadin Spring")

public class EzUI extends UI {
    private static final Logger logger = LoggerFactory.getLogger(EzUI.class);
    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    private ApplicationContext applicationContext;
    private MainPage mainPage;

    @Override
    protected void init(VaadinRequest request) {
        mainPage = applicationContext.getBean(MainPage.class);

        setErrorHandler(event -> {
            Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
            logger.error("Error during request", t);
        });

        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);

        // Initialize Navigator
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);
        navigator.setErrorView(LoginPage.class);
        setNavigator(navigator);

        updateContent();
    }

    private void updateContent() {
        if (getSession().getAttribute(EzUser.class.getName()) != null) {
            setContent(mainPage);
        } else {
            getNavigator().navigateTo(LoginPage.VIEW_NAME);
        }
    }
}
