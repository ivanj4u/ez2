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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EzAcctId implements Serializable {

	private String norek;
	private Date tgl;

	public EzAcctId() {
	}

	public EzAcctId(String norek, Date tgl) {
		this.norek = norek;
		this.tgl = tgl;
	}

	@Column(name = "no_rek", nullable = false, length = 16)
	public String getNorek() {
		return this.norek;
	}

	public void setNorek(String norek) {
		this.norek = norek;
	}

	@Column(name = "tgl", nullable = false, length = 13)
	public Date getTgl() {
		return this.tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EzAcctId))
			return false;
		EzAcctId castOther = (EzAcctId) other;

		return ((this.getNorek() == castOther.getNorek()) || (this.getNorek() != null && castOther.getNorek() != null && this.getNorek().equals(castOther.getNorek()))) && ((this.getTgl() == castOther.getTgl()) || (this.getTgl() != null && castOther.getTgl() != null && this.getTgl().equals(castOther.getTgl())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getNorek() == null ? 0 : this.getNorek().hashCode());
		result = 37 * result + (getTgl() == null ? 0 : this.getTgl().hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		StringBuffer embededId = new StringBuffer();
		embededId.append(getNorek());
		embededId.append("#");
		embededId.append(new SimpleDateFormat("yyyyMMdd").format(getTgl()));
		return embededId.toString();
	}

}
