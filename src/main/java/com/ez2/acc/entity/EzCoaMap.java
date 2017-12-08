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
@Table(name = "ez_coa_map", schema = "ez")
public class EzCoaMap implements Serializable {

    @Id
    @Column(name = "no_coa", nullable = false, length = 13)
    private String noCoa;
    @Column(name = "company_code", length = 5)
    private String companyCode;
    @Column(name = "ccy", length = 3)
    private String ccy;
    @Column(name = "parent_coa", length = 13)
    private String parentCOA;
    @Column(name = "mutasi_d", nullable = false, precision = 20, scale = 2)
    private BigDecimal mutasiD;
    @Column(name = "mutasi_c", nullable = false, precision = 20, scale = 2)
    private BigDecimal mutasiC;
    @Column(name = "saldo_awal", nullable = false, precision = 20, scale = 2)
    private BigDecimal saldoAwal;
    @Column(name = "saldo_akhir", nullable = false, precision = 20, scale = 2)
    private BigDecimal saldoAkhir;
    @Column(name = "no_coa_master", length = 5)
    private String noCOAMaster;
    @Temporal(TemporalType.DATE)
    @Column(name = "last_update", length = 7)
    private Date lastUpdate;

    public EzCoaMap() {
    }

    public String getNoCoa() {
        return this.noCoa;
    }

    public void setNoCoa(String noCoa) {
        this.noCoa = noCoa;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCcy() {
        return this.ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getParentCOA() {
        return this.parentCOA;
    }

    public void setParentCOA(String parentCOA) {
        this.parentCOA = parentCOA;
    }

    public BigDecimal getMutasiD() {
        return this.mutasiD;
    }

    public void setMutasiD(BigDecimal mutasiD) {
        this.mutasiD = mutasiD;
    }

    public BigDecimal getMutasiC() {
        return this.mutasiC;
    }

    public void setMutasiC(BigDecimal mutasiC) {
        this.mutasiC = mutasiC;
    }

    public BigDecimal getSaldoAwal() {
        return this.saldoAwal;
    }

    public void setSaldoAwal(BigDecimal saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    public BigDecimal getSaldoAkhir() {
        return this.saldoAkhir;
    }

    public void setSaldoAkhir(BigDecimal saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }

    public String getNoCOAMaster() {
        return this.noCOAMaster;
    }

    public void setNoCOAMaster(String noCOAMaster) {
        this.noCOAMaster = noCOAMaster;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
