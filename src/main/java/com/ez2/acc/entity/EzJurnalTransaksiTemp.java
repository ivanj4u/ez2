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
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ez_jurnal_transaksi_temp", schema = "ez")
@IdClass(EzJurnalTransaksiTempId.class)
public class EzJurnalTransaksiTemp implements Serializable {

	private long idJurnalTransaksi;
	private long jurnalId;

	private String noRek;
	private String noCoa;
	private BigDecimal amountD;
	private BigDecimal amountC;
	private Date tglPosting;
	private Date waktu;
	private Date tglTransaksi;
	private String keterangan;

	public EzJurnalTransaksiTemp() {
	}

	@Id
	@Column(name = "id_jurnal_transaksi_temp", nullable = false)
	public long getIdJurnalTransaksi() {
		return this.idJurnalTransaksi;
	}

	public void setIdJurnalTransaksi(long idJurnalTransaksi) {
		this.idJurnalTransaksi = idJurnalTransaksi;
	}

	@Id
	@Column(name = "jurnal_id", nullable = false)
	public long getJurnalId() {
		return this.jurnalId;
	}

	public void setJurnalId(long jurnalId) {
		this.jurnalId = jurnalId;
	}

	@Column(name = "no_rek", nullable = false, length = 16)
	public String getNoRek() {
		return this.noRek;
	}

	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}

	@Column(name = "no_coa", nullable = false, length = 16)
	public String getNoCoa() {
		return this.noCoa;
	}

	public void setNoCoa(String noCoa) {
		this.noCoa = noCoa;
	}

	@Column(name = "amount_d", precision = 20, scale = 2)
	public BigDecimal getAmountD() {
		return this.amountD;
	}

	public void setAmountD(BigDecimal amountD) {
		this.amountD = amountD;
	}

	@Column(name = "amount_c", precision = 20, scale = 2)
	public BigDecimal getAmountC() {
		return this.amountC;
	}

	public void setAmountC(BigDecimal amountC) {
		this.amountC = amountC;
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
	@Column(name = "WAKTU", length = 29)
	public Date getWaktu() {
		return this.waktu;
	}

	public void setWaktu(Date waktu) {
		this.waktu = waktu;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tgl_transaksi", length = 13)
	public Date getTglTransaksi() {
		return this.tglTransaksi;
	}

	public void setTglTransaksi(Date tglTransaksi) {
		this.tglTransaksi = tglTransaksi;
	}

	@Column(name = "keterangan", length = 200)
	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}
