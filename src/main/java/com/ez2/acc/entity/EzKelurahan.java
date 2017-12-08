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
@Table(name = "ez_kelurahan", schema = "ez")
public class EzKelurahan extends AuditTrail implements Serializable {

	private String idKelurahan;
	private String namaKelurahan;
	private String kodePos;
	private String kodeKecamatan;

	public EzKelurahan() {
	}

	@Id
	@Column(name = "id_kelurahan", nullable = false, length = 10)
	public String getIdKelurahan() {
		return this.idKelurahan;
	}

	public void setIdKelurahan(String idKelurahan) {
		this.idKelurahan = idKelurahan;
	}
	
	@Id
	@Column(name = "kode_kecamatan", nullable = false, length = 6)
	public String getKodeKecamatan() {
		return this.kodeKecamatan;
	}

	public void setKodeKecamatan(String kodeKecamatan) {
		this.kodeKecamatan = kodeKecamatan;
	}

	@Column(name = "nama_kelurahan", nullable = false, length = 100)
	public String getNamaKelurahan() {
		return this.namaKelurahan;
	}

	public void setNamaKelurahan(String namaKelurahan) {
		this.namaKelurahan = namaKelurahan;
	}

	@Column(name = "kode_pos", length = 6)
	public String getKodePos() {
		return this.kodePos;
	}

	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}

}