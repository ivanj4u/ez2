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
@Table(name = "ez_company", schema = "ez")
public class EzCompany extends AuditTrail implements Serializable {
	
	private String companyCode;
	private String name;
	private String alamat;
	private String kota;
	private String idKelurahan;
	private String telp;
	private String fax;
	private String kodeParent;
	private String status;
	private String rt;
	private String rw;

	public EzCompany() {
	}

	@Id
	@Column(name = "company_code", unique = true, nullable = false, length = 5)
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Column(name = "company_name", unique = false, nullable = true, length = 64, scale = 0)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "alamat", unique = false, nullable = true, length = 128, scale = 0)
	public String getAlamat() {
		return this.alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	@Column(name = "kota", unique = false, nullable = true, length = 4, scale = 0)
	public String getKota() {
		return this.kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	@Column(name = "id_kelurahan", unique = false, nullable = true, length = 20, scale = 0)
	public String getIdKelurahan() {
		return this.idKelurahan;
	}

	public void setIdKelurahan(String idKelurahan) {
		this.idKelurahan = idKelurahan;
	}

	@Column(name = "telp", unique = false, nullable = true, length = 16, scale = 0)
	public String getTelp() {
		return this.telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	@Column(name = "fax", unique = false, nullable = true, length = 16, scale = 0)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "kd_parent", unique = false, nullable = true, length = 5, scale = 0)
	public String getKodeParent() {
		return this.kodeParent;
	}

	public void setKodeParent(String kodeParent) {
		this.kodeParent = kodeParent;
	}

	@Column(name = "status", unique = false, nullable = true, length = 1, scale = 0)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "rt", unique = false, nullable = true, length = 3, scale = 0)
	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	@Column(name = "rw", unique = false, nullable = true, length = 3, scale = 0)
	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

}