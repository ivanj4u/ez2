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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ez_transaction_handler", schema = "ez")
public class EzTransactionHandler extends AuditTrail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String kodeTransaksi;
	private String description;
	private String className;
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

	@Id
	@Column(name = "kode_transaksi", nullable = false, length = 5)
	public String getKodeTransaksi() {
		return this.kodeTransaksi;
	}

	public void setKodeTransaksi(String kodeTransaksi) {
		this.kodeTransaksi = kodeTransaksi;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "class_name", length = 200)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_change", nullable = false, length = 29)
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
