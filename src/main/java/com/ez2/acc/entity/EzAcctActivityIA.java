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

	private String norek;
	private Date tgl;
	private String noCOA;
	private BigDecimal saldoAwal;
	private BigDecimal mutasiD;
	private BigDecimal mutasiK;
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

	@Column(name = "no_coa", length = 13)
	public String getNoCOA() {
		return this.noCOA;
	}

	public void setNoCOA(String noCOA) {
		this.noCOA = noCOA;
	}

	@Column(name = "saldo_awal", nullable = false, precision = 20,scale=2)
	public BigDecimal getSaldoAwal() {
		return this.saldoAwal;
	}

	public void setSaldoAwal(BigDecimal saldoAwal) {
		this.saldoAwal = saldoAwal;
	}

	@Column(name = "mutasi_d", precision = 20,scale=2)
	public BigDecimal getMutasiD() {
		return this.mutasiD;
	}

	public void setMutasiD(BigDecimal mutasiD) {
		this.mutasiD = mutasiD;
	}

	@Column(name = "mutasi_k", precision = 20,scale=2)
	public BigDecimal getMutasiK() {
		return this.mutasiK;
	}

	public void setMutasiK(BigDecimal mutasiK) {
		this.mutasiK = mutasiK;
	}

	@Column(name = "saldo_akhir", nullable = false, precision = 20,scale =2)
	public BigDecimal getSaldoAkhir() {
		return this.saldoAkhir;
	}

	public void setSaldoAkhir(BigDecimal saldoAkhir) {
		this.saldoAkhir = saldoAkhir;
	}
	
	@Id
	@Column(name = "no_rek", nullable = false, length = 16)
	public String getNorek() {
		return this.norek;
	}

	public void setNorek(String norek) {
		this.norek = norek;
	}

	@Id
	@Column(name = "tgl", nullable = false, length = 13)
	public Date getTgl() {
		return this.tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}
}
