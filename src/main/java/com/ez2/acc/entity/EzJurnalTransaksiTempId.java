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
public class EzJurnalTransaksiTempId implements Serializable {

	private long idJurnalTransaksi;
	private long jurnalId;

	public EzJurnalTransaksiTempId() {
	}

	public EzJurnalTransaksiTempId(long idJurnalTransaksi, long jurnalId) {
		this.idJurnalTransaksi = idJurnalTransaksi;
		this.jurnalId = jurnalId;
	}

	public long getIdJurnalTransaksi() {
		return this.idJurnalTransaksi;
	}

	public void setIdJurnalTransaksi(long idJurnalTransaksi) {
		this.idJurnalTransaksi = idJurnalTransaksi;
	}

	public long getJurnalId() {
		return this.jurnalId;
	}

	public void setJurnalId(long jurnalId) {
		this.jurnalId = jurnalId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EzJurnalTransaksiTempId))
			return false;
		EzJurnalTransaksiTempId castOther = (EzJurnalTransaksiTempId) other;

		return (this.getIdJurnalTransaksi() == castOther.getIdJurnalTransaksi())
				&& (this.getJurnalId() == castOther.getJurnalId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIdJurnalTransaksi();
		result = 37 * result + (int) this.getJurnalId();
		return result;
	}

	@Override
	public String toString() {
		StringBuffer embededId = new StringBuffer();
		embededId.append(getIdJurnalTransaksi());
		embededId.append("#");
		embededId.append(getJurnalId());
		return embededId.toString();
	}
}
