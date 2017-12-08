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
@Table(name = "ez_rekening_ia_master", schema = "ez")
public class EzRekeningIaMaster extends AuditTrail implements Serializable {

	private String noRek;
	private String noCoa;
	private String description;
	private String saldoNormal;

	public EzRekeningIaMaster() {
	}

	public EzRekeningIaMaster(String noRek) {
		this.noRek = noRek;
	}

	public EzRekeningIaMaster(String noRek, String noCoa, String description,
			String saldoNormal) {
		this.noRek = noRek;
		this.noCoa = noCoa;
		this.description = description;
		this.saldoNormal = saldoNormal;
	}

	@Id
	@Column(name = "no_rek", nullable = false, length = 7)
	public String getNoRek() {
		return this.noRek;
	}

	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}

	@Column(name = "no_coa", length = 5)
	public String getNoCoa() {
		return this.noCoa;
	}

	public void setNoCoa(String noCoa) {
		this.noCoa = noCoa;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "saldo_normal", length = 1)
	public String getSaldoNormal() {
		return this.saldoNormal;
	}

	public void setSaldoNormal(String saldoNormal) {
		this.saldoNormal = saldoNormal;
	}

}
