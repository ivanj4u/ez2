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

package com.ez2.acc.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ez_lookup", schema = "ez")
@IdClass(EzLookupId.class)
public class EzLookup extends AuditTrail implements Serializable {

    @Id
    @Column(name = "lookup_name", nullable = false, length = 100)
    private String lookupName;
    @Id
    @Column(name = "lookup_value", nullable = false, length = 100)
    private String lookupValue;
    @Column(name = "lookup_label", nullable = false, length = 200)
    private String lookupLabel;
    @Column(name = "no_urut", length = 3)
    private long noUrut;

    public EzLookup() {
    }

    public String getLookupName() {
        return lookupName;
    }

    public void setLookupName(String lookupName) {
        this.lookupName = lookupName;
    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }

    public String getLookupLabel() {
        return lookupLabel;
    }

    public void setLookupLabel(String lookupLabel) {
        this.lookupLabel = lookupLabel;
    }

    public long getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(long noUrut) {
        this.noUrut = noUrut;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof EzLookupId))
            return false;
        EzLookupId castOther = (EzLookupId) other;

        return ((this.getLookupName() == castOther.getLookupName()) || (this.getLookupName() != null && castOther.getLookupName() != null && this.getLookupName().equals(castOther.getLookupName()))) && ((this.getLookupValue() == castOther.getLookupValue()) || (this.getLookupValue() != null && castOther.getLookupValue() != null && this.getLookupValue().equals(castOther.getLookupValue())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getLookupName() == null ? 0 : this.getLookupName().hashCode());
        result = 37 * result + (getLookupValue() == null ? 0 : this.getLookupValue().hashCode());
        return result;
    }
}
