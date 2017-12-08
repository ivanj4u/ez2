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
@Table(name = "ez_jurnal", schema = "ez")
public class EzJurnal extends AuditTrail implements Serializable {

	private long jurnalId;
	private String kodeTransaksi;
	private String keterangan;
	private String isAuthorized;
	private String authorizedBy;
	private Date authorizedDate;
	private String proposedBy;
	private Date proposedDate;
	private String companyCode;
	private String refId;
	private String reverse;
	private String respCode;
	private Date tglTransaksi;
	private Date tglPosting;

	public EzJurnal() {
	}

	public EzJurnal(long jurnalId) {
		this.jurnalId = jurnalId;
	}

	public EzJurnal(long nextSequenceOfJurnalId, String transactionCode, Date tglTransaksi, Date tglPosting) {
		this.jurnalId = nextSequenceOfJurnalId;
		this.kodeTransaksi = transactionCode;
		this.tglTransaksi = tglTransaksi;
		this.tglPosting = tglPosting;
	}

	@Id
	@Column(name = "jurnal_id", nullable = false)
	public long getJurnalId() {
		return this.jurnalId;
	}

	public void setJurnalId(long jurnalId) {
		this.jurnalId = jurnalId;
	}

	@Column(name = "kode_transaksi", length = 5)
	public String getKodeTransaksi() {
		return this.kodeTransaksi;
	}

	public void setKodeTransaksi(String kodeTransaksi) {
		this.kodeTransaksi = kodeTransaksi;
	}

	@Column(name = "keterangan", length = 200)
	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	@Column(name = "is_authorized", length = 1)
	public String getIsAuthorized() {
		return this.isAuthorized;
	}

	public void setIsAuthorized(String isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	@Column(name = "authorized_by", length = 10)
	public String getAuthorizedBy() {
		return this.authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	@Column(name = "proposed_by", length = 10)
	public String getProposedBy() {
		return this.proposedBy;
	}

	public void setProposedBy(String proposedBy) {
		this.proposedBy = proposedBy;
	}

	@Column(name = "company_code", length = 5)
	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "authorized_date", length = 29)
	public Date getAuthorizedDate() {
		return this.authorizedDate;
	}

	public void setAuthorizedDate(Date authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	@Column(name = "ref_id", length = 30)
	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	@Column(name = "is_reverse", length = 1)
	public String getReverse() {
		return this.reverse;
	}

	public void setReverse(String reverse) {
		this.reverse = reverse;
	}

	@Column(name = "resp_code", length = 10)
	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tgl_transaksi", length = 13)
	public Date getTglTransaksi() {
		return this.tglTransaksi;
	}

	public void setTglTransaksi(Date tglTransaksi) {
		this.tglTransaksi = tglTransaksi;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tgl_posting", length = 13)
	public Date getTglPosting() {
		return this.tglPosting;
	}

	public void setTglPosting(Date tglPosting) {
		this.tglPosting = tglPosting;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "proposed_date", length = 29)
	public Date getProposedDate() {
		return this.proposedDate;
	}

	public void setProposedDate(Date proposedDate) {
		this.proposedDate = proposedDate;
	}

}
