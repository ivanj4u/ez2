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

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EzLookupId implements Serializable {

	private String lookupName;
	private String lookupValue;

	public EzLookupId() {
	}

	public EzLookupId(String lookupName, String lookupValue) {
		this.lookupName = lookupName;
		this.lookupValue = lookupValue;
	}

	public String getLookupName() {
		return this.lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getLookupValue() {
		return this.lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
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
	
	@Override
	public String toString() {
		StringBuffer embededId = new StringBuffer();
		embededId.append(getLookupName());
		embededId.append("#");
		embededId.append(getLookupValue());
		return embededId.toString();
	}

}
