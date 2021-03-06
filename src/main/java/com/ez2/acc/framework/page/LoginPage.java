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

import com.ez2.acc.entity.EzUser;
import com.ez2.acc.loader.MenuLoader;
import com.ez2.acc.services.ParamServices;
import com.ez2.acc.services.SessionServices;
import com.ez2.acc.services.UserServices;
import com.ez2.acc.util.ValidationHelper;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Date;

@SuppressWarnings("deprecation")
@UIScope
@SpringView(name = LoginPage.VIEW_NAME)
public class LoginPage extends VerticalLayout implements View {
    public static final String VIEW_NAME = "login";
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    @Autowired
    private UserServices servicesUser;
    @Autowired
    private ParamServices servicesParam;
    @Autowired
    private SessionServices servicesSession;
    @Autowired
    private MenuLoader loaderMenu;
    private TextField txtUsername;
    private TextField txtPassword;
    private Button btnLogin;

    @PostConstruct
    private void init() {
        setSizeFull();
        setMargin(false);
        setSpacing(true);

        Component loginForm = initComponent();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        Notification notification = new Notification(
                "Selamat Datang ke Aplikasi Vaadin Spring");
        notification
                .setDescription("<span>Untuk mendapatkan <b>Username</b> & <b>Password</b> dapat menghubungi : <a href=\"https://twitter.com/ivan_j4u\">Ivan Aribanilia</a> .</span>" +
                        "<span> Terima kasih </span>");
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(20000);
        notification.show(UI.getCurrent().getPage());
    }

    private Component initComponent() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setMargin(true);
        loginPanel.addStyleName(ValoTheme.PANEL_WELL);
        Responsive.makeResponsive(loginPanel);

        loginPanel.addComponent(initDetail());
        return loginPanel;
    }

    private Component initDetail() {
        VerticalLayout layout = new VerticalLayout();

        Label title = new Label("Simple Vaadin Application");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H3);
        title.addStyleName(ValoTheme.LABEL_LIGHT);
        layout.addComponent(title, 0);

        layout.addComponent(txtUsername = new TextField("Username"));
        txtUsername.setIcon(FontAwesome.USER);
        txtUsername.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        layout.addComponent(txtPassword = new PasswordField("Password"));
        txtPassword.setIcon(FontAwesome.LOCK);
        txtPassword.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        layout.addComponent(btnLogin = new Button("Login"));
        btnLogin.addClickListener(event -> {
            if (ValidationHelper.validateFieldWithCaption(txtUsername)
                    && ValidationHelper.validateFieldWithCaption(txtPassword)) {
                try {
                    attemptLogin(txtUsername.getValue(), txtPassword.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        });
        btnLogin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnLogin.focus();

        return layout;
    }

    private void attemptLogin(String username, String password) {
        try {
            EzUser user = servicesUser.getUser(username);
            if (user != null) {
                // Cek Password
                if (user.getPassword().equals(password)) {
                    if (checkLogin(user, true)) {
                        // Set Authorized Menu
                        loaderMenu.setAuthorizedMenu(user);
                        // Set User pada Session
                        VaadinSession.getCurrent().setAttribute(EzUser.class, user);
                        // Reset Field
                        resetField();
                        // Navigate to MainPage
                        UI.getCurrent().getNavigator().navigateTo(MainPage.VIEW_NAME);
                    } else {
                        logger.error("Status User : " + user.getUserId() + " tidak benar!");
                        showFailedLogin("Status User : " + user.getUserId() + " tidak benar!");
                    }
                } else {
                    // Cek maksimal salah password
                    checkLogin(user, false);
                    showFailedLogin("Login Gagal!. Username atau Password yang anda masukkan salah!");
                }
            } else {
                logger.error("User : " + txtUsername.getValue() + " tidak ditemukan!");
                showFailedLogin("User : " + txtUsername.getValue() + " tidak ditemukan!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            showFailedLogin("Login Gagal!. Silahkan hubungi administrator!");
        }
    }

    private boolean checkLogin(EzUser user, boolean valid) {
        try {
            Date now = new Date();
            boolean b = user.getStartTime().getTime() <= now.getTime();
            b = b && now.getTime() <= user.getEndTime().getTime();
            b = b && (user.getLoginFailCount() != null ? user.getLoginFailCount() : 0)
                    <= Integer.parseInt(servicesParam.getParam("MAX.LOGIN.FAIL").getValue());
            b = b && user.getStatus() != null && user.getStatus().equals("1");
            if (!b) {
                showFailedLogin("Peringatan, Profil anda diblokir atau tidak aktif. Silahkan hubungi administrator!");
                valid = false;
            }
            if (valid) {
                user.setLoginFailCount(0);
                user.setLastLogin(now);
                servicesSession.sessionLogin(user.getUserId(), VaadinSession.getCurrent().getSession().getId(), getUI().getPage().getWebBrowser().getAddress());
            } else {
                user.setLoginFailCount(user.getLoginFailCount() + 1);
            }
            if (now.getTime() > user.getEndTime().getTime() && user.getStatus().equals("1")) {
                // set status nonaktif
                user.setStatus("0");
            }
            servicesUser.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return valid;
    }

    private void showFailedLogin(String message) {
        resetField();
        Notification.show(message, Notification.Type.ERROR_MESSAGE);
    }

    private void resetField() {
        txtUsername.setValue("");
        txtPassword.setValue("");
    }
}
