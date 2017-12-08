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

    @Id
    @Column(name = "jurnal_id", nullable = false)
    private long jurnalId;
    @Column(name = "kode_transaksi", length = 5)
    private String kodeTransaksi;
    @Column(name = "keterangan", length = 200)
    private String keterangan;
    @Column(name = "proposed_by")
    private String proposedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "proposed_date", length = 29)
    private Date proposedDate;
    @Column(name = "authorized_by")
    private String authorizedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "authorized_date", length = 29)
    private Date authorizedDate;
    @Column(name = "is_authorized", length = 1)
    private String isAuthorized;
    @Column(name = "company_code", length = 5)
    private String companyCode;
    @Column(name = "ref_id", length = 30)
    private String refId;
    @Column(name = "is_reverse", length = 1)
    private String isReverse;
    @Column(name = "resp_code", length = 10)
    private String respCode;
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_transaksi", length = 13)
    private Date tglTransaksi;
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_posting", length = 13)
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

    public long getJurnalId() {
        return jurnalId;
    }

    public void setJurnalId(long jurnalId) {
        this.jurnalId = jurnalId;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getProposedBy() {
        return proposedBy;
    }

    public void setProposedBy(String proposedBy) {
        this.proposedBy = proposedBy;
    }

    public Date getProposedDate() {
        return proposedDate;
    }

    public void setProposedDate(Date proposedDate) {
        this.proposedDate = proposedDate;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public Date getAuthorizedDate() {
        return authorizedDate;
    }

    public void setAuthorizedDate(Date authorizedDate) {
        this.authorizedDate = authorizedDate;
    }

    public String getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(String isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getIsReverse() {
        return isReverse;
    }

    public void setIsReverse(String isReverse) {
        this.isReverse = isReverse;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public Date getTglPosting() {
        return tglPosting;
    }

    public void setTglPosting(Date tglPosting) {
        this.tglPosting = tglPosting;
    }
}
