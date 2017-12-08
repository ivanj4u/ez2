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
@Table(name = "ez_acct_activity_ia", schema = "ez")
@IdClass(EzAcctId.class)
public class EzAcctActivityIA implements Serializable {

	@Id
	@Column(name = "no_rek", nullable = false, length = 16)
	private String norek;
	@Id
	@Column(name = "tgl", nullable = false, length = 13)
	private Date tgl;
	@Column(name = "no_coa", length = 13)
	private String noCOA;
	@Column(name = "saldo_awal", nullable = false, precision = 20, scale = 2)
	private BigDecimal saldoAwal;
	@Column(name = "mutasi_d", precision = 20, scale = 2)
	private BigDecimal mutasiD;
	@Column(name = "mutasi_k", precision = 20, scale = 2)
	private BigDecimal mutasiK;
	@Column(name = "saldo_akhir", nullable = false, precision = 20, scale = 2)
	private BigDecimal saldoAkhir;

	public EzAcctActivityIA() {
	}

	public EzAcctActivityIA(String norek, Date tgl, BigDecimal saldoAwal, BigDecimal mutasiD, BigDecimal mutasiK, BigDecimal saldoAkhir) {
		this.norek = norek;
		this.tgl = tgl;
		this.saldoAwal = saldoAwal;
		this.mutasiD = mutasiD;
		this.mutasiK = mutasiK;
		this.saldoAkhir = saldoAkhir;
	}

	public String getNorek() {
		return this.norek;
	}

	public void setNorek(String norek) {
		this.norek = norek;
	}

	public Date getTgl() {
		return this.tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}

	public String getNoCOA() {
		return this.noCOA;
	}

	public void setNoCOA(String noCOA) {
		this.noCOA = noCOA;
	}

	public BigDecimal getSaldoAwal() {
		return this.saldoAwal;
	}

	public void setSaldoAwal(BigDecimal saldoAwal) {
		this.saldoAwal = saldoAwal;
	}

	public BigDecimal getMutasiD() {
		return this.mutasiD;
	}

	public void setMutasiD(BigDecimal mutasiD) {
		this.mutasiD = mutasiD;
	}

	public BigDecimal getMutasiK() {
		return this.mutasiK;
	}

	public void setMutasiK(BigDecimal mutasiK) {
		this.mutasiK = mutasiK;
	}

	public BigDecimal getSaldoAkhir() {
		return this.saldoAkhir;
	}

	public void setSaldoAkhir(BigDecimal saldoAkhir) {
		this.saldoAkhir = saldoAkhir;
	}
	
}
