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

import com.ez2.acc.container.TreeMenuContainer;
import com.ez2.acc.framework.component.ItemComponent;
import com.ez2.acc.framework.component.PopUpComboBox;
import com.ez2.acc.framework.impl.AbstractScreen;
import com.ez2.acc.framework.listener.CallbackListener;
import com.ez2.acc.util.ValidationHelper;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

@UIScope
public class DetailMenuContainerView extends AbstractScreen {

    private TextField txtMenuId, txtMenuName;
    private PopUpComboBox cmbAdd, cmbEdit, cmbView, cmbDelete;
    private Button btnDone;
    private CallbackListener listener;
    private TreeMenuContainer menuContainer;

    public DetailMenuContainerView(CallbackListener listener) {
        super();
        this.listener = listener;
        setCaption("Detail Priviledge");
    }

    @Override
    protected void initComponents() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(initDetail());
        setContent(layout);
    }

    private Component initDetail() {
        VerticalLayout layout = new VerticalLayout();
        GridLayout grid = new GridLayout(2, 7);
        int row = 0;
        grid.addComponent(new Label("Id Menu"), 0, row);
        grid.addComponent(txtMenuId = new TextField(), 1, row++);
        txtMenuId.setEnabled(false);
        grid.addComponent(new Label("Nama Menu"), 0, row);
        grid.addComponent(txtMenuName = new TextField(), 1, row++);
        txtMenuName.setEnabled(false);
        Label lbl = new Label("Priviledge Tambah");
        lbl.setWidth("145px");
        grid.addComponent(lbl, 0, row);
        grid.addComponent(cmbAdd = new PopUpComboBox(), 1, row++);
        createComboBoxData(cmbAdd);
        grid.addComponent(new Label("Priviledge Ubah"), 0, row);
        grid.addComponent(cmbEdit = new PopUpComboBox(), 1, row++);
        createComboBoxData(cmbEdit);
        grid.addComponent(new Label("Priviledge Lihat"), 0, row);
        grid.addComponent(cmbView = new PopUpComboBox(), 1, row++);
        createComboBoxData(cmbView);
        grid.addComponent(new Label("Priviledge Hapus"), 0, row);
        grid.addComponent(cmbDelete = new PopUpComboBox(), 1, row++);
        createComboBoxData(cmbDelete);
        grid.addComponent(btnDone = new Button("Selesai"), 1, row++);
        btnDone.addClickListener(event -> doCallback());

        layout.addComponent(grid);
        return layout;
    }

    private void createComboBoxData(PopUpComboBox cmb) {
        cmb.addItem(new ItemComponent("0", "Tidak"));
        cmb.addItem(new ItemComponent("1", "Iya"));
    }

    private void doCallback() {
        if (ValidationHelper.validateValueNotNull(cmbAdd.getValue())
                && ValidationHelper.validateValueNotNull(cmbEdit.getValue())
                && ValidationHelper.validateValueNotNull(cmbView.getValue())
                && ValidationHelper.validateValueNotNull(cmbDelete.getValue())) {
        }
        if (listener != null) {
            menuContainer.setIsAdd(cmbAdd.getValue().getValue().toString());
            menuContainer.setIsUpdate(cmbEdit.getValue().getValue().toString());
            menuContainer.setIsView(cmbView.getValue().getValue().toString());
            menuContainer.setIsDelete(cmbDelete.getValue().getValue().toString());
            listener.onCallback(menuContainer);
        }
    }

    protected void unpack() {
        this.menuContainer = (TreeMenuContainer) getParam();

        txtMenuId.setValue(menuContainer.getMenuId());
        txtMenuName.setValue(menuContainer.getMenuName());
        cmbAdd.setValueItem(menuContainer.getIsAdd());
        cmbEdit.setValueItem(menuContainer.getIsUpdate());
        cmbView.setValueItem(menuContainer.getIsView());
        cmbDelete.setValueItem(menuContainer.getIsDelete());
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

    @Override
    public boolean isAuthorizedToAdd() {
        return true;
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
