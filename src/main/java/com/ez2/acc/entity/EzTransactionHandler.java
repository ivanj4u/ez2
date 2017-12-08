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
import java.util.Date;

@Entity
@Table(name = "ez_transaction_handler", schema = "ez")
public class EzTransactionHandler extends AuditTrail implements Serializable {

	@Id
	@Column(name = "kode_transaksi", nullable = false, length = 5)
	private String kodeTransaksi;
	@Column(name = "description", length = 200)
	private String description;
	@Column(name = "class_name", length = 200)
	private String className;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_change", nullable = false)
	private Date lastChange;

	public EzTransactionHandler() {
	}

	public EzTransactionHandler(String kodeTransaksi, Date lastChange) {
		this.kodeTransaksi = kodeTransaksi;
		this.lastChange = lastChange;
	}

	public EzTransactionHandler(String kodeTransaksi, String description, String className, Date lastChange) {
		this.kodeTransaksi = kodeTransaksi;
		this.description = description;
		this.className = className;
		this.lastChange = lastChange;
	}

	public String getKodeTransaksi() {
		return this.kodeTransaksi;
	}

	public void setKodeTransaksi(String kodeTransaksi) {
		this.kodeTransaksi = kodeTransaksi;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getLastChange() {
		return this.lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblTransactionHandler")
//	public Set<TblTransactionParam> getTblTransactionParams() {
//		return this.tblTransactionParams;
//	}
//
//	public void setTblTransactionParams(Set<TblTransactionParam> tblTransactionParams) {
//		this.tblTransactionParams = tblTransactionParams;
//	}

}
