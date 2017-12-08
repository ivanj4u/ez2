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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ez_provinsi", schema = "ez")
public class EzProvinsi extends AuditTrail implements Serializable {

	private String kodeProvinsi;
	private String namaProvinsi;

	public EzProvinsi() {
	}

	public EzProvinsi(String kodeProvinsi, String namaProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
		this.namaProvinsi = namaProvinsi;
	}

	@Id
	@Column(name = "kode_provinsi", nullable = false, length = 2)
	public String getKodeProvinsi() {
		return this.kodeProvinsi;
	}

	public void setKodeProvinsi(String kodeProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
	}

	@Column(name = "nama_provinsi", nullable = false, length = 100)
	public String getNamaProvinsi() {
		return this.namaProvinsi;
	}

	public void setNamaProvinsi(String namaProvinsi) {
		this.namaProvinsi = namaProvinsi;
	}

}
