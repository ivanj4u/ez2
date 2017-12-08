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

    @Id
    @Column(name = "id_jurnal_transaksi_temp", nullable = false)
    private long idJurnalTransaksi;
    @Id
    @Column(name = "jurnal_id", nullable = false)
    private long jurnalId;
    @Column(name = "no_rek", nullable = false, length = 16)
    private String noRek;
    @Column(name = "no_coa", nullable = false, length = 16)
    private String noCoa;
    @Column(name = "amount_d", precision = 20, scale = 2)
    private BigDecimal amountD;
    @Column(name = "amount_c", precision = 20, scale = 2)
    private BigDecimal amountC;
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_posting", length = 13)
    private Date tglPosting;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "waktu", length = 29)
    private Date waktu;
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_transaksi", length = 13)
    private Date tglTransaksi;
    @Column(name = "keterangan", length = 200)
    private String keterangan;

    public EzJurnalTransaksiTemp() {
    }

    public long getIdJurnalTransaksi() {
        return this.idJurnalTransaksi;
    }

    public void setIdJurnalTransaksi(long idJurnalTransaksi) {
        this.idJurnalTransaksi = idJurnalTransaksi;
    }

    public long getJurnalId() {
        return this.jurnalId;
    }

    public void setJurnalId(long jurnalId) {
        this.jurnalId = jurnalId;
    }

    public String getNoRek() {
        return this.noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public String getNoCoa() {
        return this.noCoa;
    }

    public void setNoCoa(String noCoa) {
        this.noCoa = noCoa;
    }

    public BigDecimal getAmountD() {
        return this.amountD;
    }

    public void setAmountD(BigDecimal amountD) {
        this.amountD = amountD;
    }

    public BigDecimal getAmountC() {
        return this.amountC;
    }

    public void setAmountC(BigDecimal amountC) {
        this.amountC = amountC;
    }

    public Date getTglPosting() {
        return this.tglPosting;
    }

    public void setTglPosting(Date tglPosting) {
        this.tglPosting = tglPosting;
    }

    public Date getWaktu() {
        return this.waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public Date getTglTransaksi() {
        return this.tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
