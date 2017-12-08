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

import com.vaadin.ui.ComboBox;

import java.util.HashMap;

public class PopUpComboBox extends ComboBox<ItemComponent> {

    private final HashMap<Object, ItemComponent> h;

    public PopUpComboBox() {
        super();
        this.h = new HashMap<>();
        setEmptySelectionCaption("Pilih data");
        setEmptySelectionAllowed(false);
        setItemCaptionGenerator(ItemComponent::getCaption);
    }

    public void addItem(ItemComponent item) {
        h.put(item.getValue(), item);
        super.setItems(h.values());
    }

    public Object getValueItem() {
        ItemComponent itemComponent = getValue();
        if (itemComponent != null) {
            return itemComponent.getValue();
        }
        return null;
    }

    public void setValueItem(Object value) {
        if (h.get(value) != null) {
            setValue(h.get(value));
        } else {
            setValue((ItemComponent) h.values().toArray()[0]);
        }
    }

    @Override
    public void clear() {
        super.clear();
        h.clear();
    }
}
