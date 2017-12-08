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
@Table(name = "ez_kecamatan", schema = "ez")
public class EzKecamatan extends AuditTrail implements Serializable {

	private String kodeKecamatan;
	private String kodeKabupaten;
	private String namaKecamatan;

	public EzKecamatan() {
	}

	@Id
	@Column(name = "kode_kecamatan", nullable = false, length = 6)
	public String getKodeKecamatan() {
		return this.kodeKecamatan;
	}

	public void setKodeKecamatan(String kodeKecamatan) {
		this.kodeKecamatan = kodeKecamatan;
	}
	
	@Id
	@Column(name = "kode_kabupaten", nullable = false, length = 4)
	public String getKodeKabupaten() {
		return this.kodeKabupaten;
	}

	public void setKodeKabupaten(String kodeKabupaten) {
		this.kodeKabupaten = kodeKabupaten;
	}

	@Column(name = "nama_kecamatan", nullable = false, length = 100)
	public String getNamaKecamatan() {
		return this.namaKecamatan;
	}

	public void setNamaKecamatan(String namaKecamatan) {
		this.namaKecamatan = namaKecamatan;
	}

}
