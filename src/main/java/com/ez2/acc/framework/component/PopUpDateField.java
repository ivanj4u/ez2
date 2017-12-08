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

import com.vaadin.ui.DateField;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class PopUpDateField extends DateField {

    public PopUpDateField() {
        super();
        setDateFormat("dd-MM-yyyy");
    }

    public Date getValueDate() {
        if (getValue() != null) {
            Date date = Date.from(getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            return date;

        }
        return null;
    }

    public void setValueDate(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            setValue(cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } else {
            setValue(null);
        }
    }
}
