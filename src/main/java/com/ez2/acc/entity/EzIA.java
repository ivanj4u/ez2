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
@Table(name = "ez_ia", schema = "ez")
public class EzIA extends AuditTrail implements Serializable {

	private String noRek;
	private String noCOA;
	private String norekIAMaster;
	private String description;
	private Date tglBuka;
	private BigDecimal saldoAwal;
	private BigDecimal saldoAkhir;
	private BigDecimal mutasiD;
	private BigDecimal mutasiC;
	private String companyCode;
	private String saldoNormal;
	private Date lastTrxDate;
	private String alternateId;

	public EzIA() {
	}

	public EzIA(String noRek, String noCOA, String norekIAMaster,
			BigDecimal saldoAwal, BigDecimal saldoAkhir, BigDecimal mutasiD,
			BigDecimal mutasiC) {
		this.noRek = noRek;
		this.noCOA = noCOA;
		this.norekIAMaster = norekIAMaster;
		this.saldoAwal = saldoAwal;
		this.saldoAkhir = saldoAkhir;
		this.mutasiD = mutasiD;
		this.mutasiC = mutasiC;
	}

	public EzIA(String noRek, String noCOA, String norekIAMaster,
			String description, Date tglBuka, BigDecimal saldoAwal,
			BigDecimal saldoAkhir, BigDecimal mutasiD, BigDecimal mutasiC,
			String companyCode, String saldoNormal, Date lastTrxDate) {
		this.noRek = noRek;
		this.noCOA = noCOA;
		this.norekIAMaster = norekIAMaster;
		this.description = description;
		this.tglBuka = tglBuka;
		this.saldoAwal = saldoAwal;
		this.saldoAkhir = saldoAkhir;
		this.mutasiD = mutasiD;
		this.mutasiC = mutasiC;
		this.companyCode = companyCode;
		this.saldoNormal = saldoNormal;
		this.lastTrxDate = lastTrxDate;
	}

	@Id
	@Column(name = "no_rek", nullable = false, length = 15)
	public String getNoRek() {
		return this.noRek;
	}

	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}

	@Column(name = "no_coa", nullable = false, length = 13)
	public String getNoCOA() {
		return this.noCOA;
	}

	public void setNoCOA(String noCOA) {
		this.noCOA = noCOA;
	}

	@Column(name = "no_rek_ia_master", nullable = false, length = 7)
	public String getNorekIAMaster() {
		return this.norekIAMaster;
	}

	public void setNorekIAMaster(String norekIAMaster) {
		this.norekIAMaster = norekIAMaster;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tgl_buka", length = 13)
	public Date getTglBuka() {
		return this.tglBuka;
	}

	public void setTglBuka(Date tglBuka) {
		this.tglBuka = tglBuka;
	}

	@Column(name = "saldo_awal", nullable = false, precision = 20, scale = 2)
	public BigDecimal getSaldoAwal() {
		return this.saldoAwal;
	}

	public void setSaldoAwal(BigDecimal saldoAwal) {
		this.saldoAwal = saldoAwal;
	}

	@Column(name = "saldo_akhir", nullable = false, precision = 20, scale = 2)
	public BigDecimal getSaldoAkhir() {
		return this.saldoAkhir;
	}

	public void setSaldoAkhir(BigDecimal saldoAkhir) {
		this.saldoAkhir = saldoAkhir;
	}

	@Column(name = "mutasi_d", nullable = false, precision = 20, scale = 2)
	public BigDecimal getMutasiD() {
		return this.mutasiD;
	}

	public void setMutasiD(BigDecimal mutasiD) {
		this.mutasiD = mutasiD;
	}

	@Column(name = "mutasi_c", nullable = false, precision = 20, scale = 2)
	public BigDecimal getMutasiC() {
		return this.mutasiC;
	}

	public void setMutasiC(BigDecimal mutasiC) {
		this.mutasiC = mutasiC;
	}

	@Column(name = "company_code", length = 5)
	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Column(name = "saldo_normal", length = 1)
	public String getSaldoNormal() {
		return this.saldoNormal;
	}

	public void setSaldoNormal(String saldoNormal) {
		this.saldoNormal = saldoNormal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_trx_date", length = 13)
	public Date getLastTrxDate() {
		return this.lastTrxDate;
	}

	public void setLastTrxDate(Date lastTrxDate) {
		this.lastTrxDate = lastTrxDate;
	}

	@Column(name = "alternate_id", length = 20)
	public String getAlternateId() {
		return alternateId;
	}

	public void setAlternateId(String alternateId) {
		this.alternateId = alternateId;
	}

}
