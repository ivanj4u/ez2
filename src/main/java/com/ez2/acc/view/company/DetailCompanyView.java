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

package com.ez2.acc.view.company;

import com.ez2.acc.entity.EzCompany;
import com.ez2.acc.framework.component.ItemComponent;
import com.ez2.acc.framework.component.NotificationHelper;
import com.ez2.acc.framework.component.PopUpComboBox;
import com.ez2.acc.framework.constants.Constants;
import com.ez2.acc.framework.impl.AbstractDetailScreen;
import com.ez2.acc.framework.listener.FieldShortcutListener;
import com.ez2.acc.loader.AlamatLoader;
import com.ez2.acc.services.CompanyServices;
import com.ez2.acc.util.ValidationHelper;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Hashtable;
import java.util.Iterator;

@SpringComponent
@UIScope
public class DetailCompanyView extends AbstractDetailScreen {
    private static final Logger logger = LoggerFactory.getLogger(DetailCompanyView.class);
    @Autowired
    private CompanyServices servicesCompany;
    @Autowired
    private AlamatLoader loaderAlamat;

    // Company
    private TextField txtCompanyCode, txtName, txtEmail, txtFax, txtPhone, txtParentCode, txtParentName;
    private PopUpComboBox cmbStatus;
    // Alamat
    private TextField txtAlamat, txtRt, txtRw, txtKodePos;
    private PopUpComboBox cmbProvinsi, cmbKabupaten, cmbKecamatan, cmbKelurahan;
    private EzCompany pojoCompany;

    @Override
    protected Component initDetail() {
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName(ValoTheme.PANEL_WELL);

        layout.addComponent(initCompany());
        layout.addComponent(initAlamat());
        generateCombobox();

        return layout;
    }

    private Component initCompany() {
        GridLayout grid = new GridLayout(3, 8);
        grid.setCaption("Perusahaan");
        int row = 0;

        Label lbl = new Label("Id Perusahaan");
        lbl.setWidth("155px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtCompanyCode = new TextField(), 1, row, 2, row++);

        grid.addComponent(new Label("Nama"), 0, row);
        grid.addComponent(txtName = new TextField(), 1, row, 2, row++);

        grid.addComponent(new Label("Phone"), 0, row);
        grid.addComponent(txtPhone = new TextField(), 1, row, 2, row++);

        grid.addComponent(new Label("Email"), 0, row);
        grid.addComponent(txtEmail = new TextField(), 1, row, 2, row++);

        grid.addComponent(new Label("Fax"), 0, row);
        grid.addComponent(txtFax = new TextField(), 1, row, 2, row++);

        grid.addComponent(new Label("Id Parent"), 0, row);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(txtParentCode = new TextField());
        FieldShortcutListener listener = new FieldShortcutListener() {
            @Override
            public void onEnterKeyPressed() {
                if (ValidationHelper.validateFieldWithoutWarn(txtParentCode)) {
                    doSearchParent();
                }
            }
        };
        listener.install(txtParentCode);

        txtParentCode.addValueChangeListener(event -> doResetParent());
        horizontalLayout.addComponent(new Label(" - "));
        horizontalLayout.addComponent(txtParentName = new TextField());
        grid.addComponent(horizontalLayout, 1, row, 2, row++);

        grid.addComponent(new Label("Status"), 0, row);
        grid.addComponent(cmbStatus = new PopUpComboBox(), 1, row, 2, row++);
        cmbStatus.addItem(new ItemComponent("1", "Aktif"));
        cmbStatus.addItem(new ItemComponent("0", "Tidak Aktif"));

        return grid;
    }

    private Component initAlamat() {
        GridLayout grid = new GridLayout(3, 10);
        grid.setCaption("Alamat Perusahaan");
        int row = 0;

        Label lbl = new Label("Jalan");
        lbl.setWidth("155px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(txtAlamat = new TextField(), 1, row, 2, row++);

        grid.addComponent(new Label("RT / RW"), 0, row);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(txtRt = new TextField());
        horizontalLayout.addComponent(new Label(" s/d "));
        horizontalLayout.addComponent(txtRw = new TextField());
        grid.addComponent(horizontalLayout, 1, row, 2, row++);

        grid.addComponent(new Label("Provinsi"), 0, row);
        grid.addComponent(cmbProvinsi = new PopUpComboBox(), 1, row, 2, row++);

        grid.addComponent(new Label("Kabupaten"), 0, row);
        grid.addComponent(cmbKabupaten = new PopUpComboBox(), 1, row, 2, row++);

        grid.addComponent(new Label("Kecamatan"), 0, row);
        grid.addComponent(cmbKecamatan = new PopUpComboBox(), 1, row, 2, row++);

        grid.addComponent(new Label("Kelurahan"), 0, row);
        grid.addComponent(cmbKelurahan = new PopUpComboBox(), 1, row, 2, row++);

        grid.addComponent(new Label("Kode Pos"), 0, row);
        grid.addComponent(txtKodePos = new TextField(), 1, row, 2, row++);

        return grid;
    }

    private void generateCombobox() {
        cmbKabupaten.setEnabled(false);
        cmbKecamatan.setEnabled(false);
        cmbKelurahan.setEnabled(false);

        // Provinsi
        Hashtable<String, String> h = loaderAlamat.getProvinsi();
        for (Iterator<String> it = h.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            cmbProvinsi.addItem(new ItemComponent(key, h.get(key)));
        }
        // Listener
        cmbProvinsi.addValueChangeListener(event -> {
            cmbKabupaten.clear();
            Hashtable<String, String> hDetail =
                    loaderAlamat.getKabupatenByProvinsi(cmbProvinsi.getValueItem().toString());
            for (Iterator<String> it = hDetail.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                cmbKabupaten.addItem(new ItemComponent(key, hDetail.get(key)));
            }
            cmbKabupaten.setEnabled(true);
        });
        cmbKabupaten.addValueChangeListener(event -> {
            cmbKecamatan.clear();
            Hashtable<String, String> hDetail =
                    loaderAlamat.getKecamatanByKabupaten(cmbKabupaten.getValueItem().toString());
            for (Iterator<String> it = hDetail.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                cmbKecamatan.addItem(new ItemComponent(key, hDetail.get(key)));
            }
            cmbKecamatan.setEnabled(true);
        });
        cmbKecamatan.addValueChangeListener(event -> {
            cmbKelurahan.clear();
            Hashtable<String, String> hDetail =
                    loaderAlamat.getKelurahanByKecamatan(cmbKecamatan.getValueItem().toString());
            for (Iterator<String> it = hDetail.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                cmbKelurahan.addItem(new ItemComponent(key, hDetail.get(key)));
            }
            cmbKelurahan.setEnabled(true);
        });
        cmbKelurahan.addValueChangeListener(
                event -> txtKodePos.setValue(loaderAlamat.getKodePosByIdKelurahan(cmbKelurahan.getValueItem().toString()))
        );
    }

    private void doSearchParent() {
        try {
            EzCompany parent = servicesCompany.getCompany(txtParentCode.getValue());
            if (parent != null)
                txtParentName.setValue(parent.getName());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_SEARCH);
        }
    }

    private void doResetParent() {
        txtParentName.setValue("");
    }

    private void doResetAlamat() {
        cmbKabupaten.setEnabled(false);
        cmbKecamatan.setEnabled(false);
        cmbKelurahan.setEnabled(false);
        txtKodePos.setValue("");
    }

    @Override
    public void setModeNew() {
        doReset();
        txtCompanyCode.setEnabled(true);
        txtName.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txtFax.setEnabled(true);
        txtParentCode.setEnabled(true);
        cmbStatus.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtRw.setEnabled(true);
        txtRt.setEnabled(true);
        cmbProvinsi.setEnabled(true);

        btnSave.setVisible(true);
    }

    @Override
    public void setModeUpdate() {
        txtCompanyCode.setEnabled(false);
        txtName.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txtFax.setEnabled(true);
        txtParentCode.setEnabled(true);
        cmbStatus.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtRw.setEnabled(true);
        txtRt.setEnabled(true);
        cmbProvinsi.setEnabled(true);

        btnSave.setVisible(true);
    }

    @Override
    public void setModeView() {
        txtCompanyCode.setEnabled(false);
        txtName.setEnabled(false);
        txtPhone.setEnabled(false);
        txtEmail.setEnabled(false);
        txtFax.setEnabled(false);
        txtParentCode.setEnabled(false);
        cmbStatus.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtRw.setEnabled(false);
        txtRt.setEnabled(false);
        cmbProvinsi.setEnabled(false);

        btnSave.setVisible(false);
    }

    @Override
    protected void doSave() {
        if (!doValidate() || !doValidateValue()) {
            return;
        }
        try {
            if (getMode() == Constants.APP_MODE.MODE_NEW) {
                pojoCompany = new EzCompany();
                pojoCompany.setCompanyCode(txtCompanyCode.getValue());
            } else {
                if (!doValidateData()) {
                    return;
                }
            }
            pojoCompany.setName(txtName.getValue());
            pojoCompany.setPhone(txtPhone.getValue());
            pojoCompany.setEmail(txtEmail.getValue());
            pojoCompany.setFax(txtFax.getValue());
            pojoCompany.setParentCode(txtParentCode.getValue());
            pojoCompany.setStatus(cmbStatus.getValueItem().toString());

            if (getMode() == Constants.APP_MODE.MODE_NEW) {
                servicesCompany.save(pojoCompany);
                listener.onAfterAdded(pojoCompany);
            } else {
                servicesCompany.update(pojoCompany);
                listener.onAfterUpdated(pojoCompany);
            }
            doReset();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_NOT_SAVE);
        }
    }

    @Override
    protected boolean doValidate() {
        if (ValidationHelper.validateRequired(txtCompanyCode) && ValidationHelper.validateRequired(txtName)
                && ValidationHelper.validateRequired(txtPhone) && ValidationHelper.validateRequired(txtEmail)
                && ValidationHelper.validateRequired(txtFax) && ValidationHelper.validateRequired(txtParentCode)
                && ValidationHelper.validateRequired(txtParentName) && ValidationHelper.validateRequired(txtAlamat)
                && ValidationHelper.validateValueNotNull(cmbStatus.getValue()) && ValidationHelper.validateValueNotNull(cmbProvinsi.getValue())
                && ValidationHelper.validateValueNotNull(cmbKecamatan.getValue()))
            return true;

        NotificationHelper.showNotification(Constants.APP_MESSAGE.WARN_DATA_MANDATORY);
        return false;
    }

    private boolean doValidateValue() {
        return true;
    }

    private boolean doValidateData() {
        EzCompany companyDatabase = servicesCompany.getCompany(pojoCompany.getCompanyCode());
        if (companyDatabase.getVersi() > pojoCompany.getVersi()) {
            NotificationHelper.showNotification(Constants.APP_MESSAGE.ERR_DATA_IN_VALID);
            return false;
        }
        return true;
    }

    @Override
    protected void doReset() {
        txtCompanyCode.setValue("");
        txtName.setValue("");
        txtPhone.setValue("");
        txtEmail.setValue("");
        txtFax.setValue("");
        txtParentCode.setValue("");
        cmbStatus.setValue(null);

        doResetAlamat();

        pojoCompany = null;
    }

    @Override
    protected void setContentById(Object pojo) {
        try {
            pojoCompany = pojo != null ? ((EzCompany) pojo) : null;
            if (pojoCompany != null) {
                txtCompanyCode.setValue(pojoCompany.getCompanyCode());
                txtName.setValue(pojoCompany.getName());
                txtPhone.setValue(pojoCompany.getPhone());
                txtEmail.setValue(pojoCompany.getEmail());
                txtFax.setValue(pojoCompany.getFax());
                txtParentCode.setValue(pojoCompany.getParentCode());
                doSearchParent();
                cmbStatus.setValueItem(pojoCompany.getStatus());
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
