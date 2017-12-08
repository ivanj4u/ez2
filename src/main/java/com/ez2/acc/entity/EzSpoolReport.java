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
@Table(name = "ez_spool_report", schema = "ez")
public class EzSpoolReport implements Serializable {

    @Id
    @Column(name = "spool_id", nullable = false)
    private long spoolId;
    @Column(name = "report_name", length = 35)
    private String reportName;
    @Column(name = "ref_id", length = 20)
    private String refId;
    @Temporal(TemporalType.DATE)
    @Column(name = "business_date", length = 13)
    private Date businessDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "initial_by")
    private String initialBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", length = 29)
    private Date createdDate;
    @Column(name = "company_code", length = 5)
    private String companyCode;
    @Column(name = "type", length = 4)
    private String type;
    @Column(name = "byte_report", length = 10)
    private byte[] byteReport;

    public EzSpoolReport() {
    }

    public EzSpoolReport(long spoolId) {
        this.spoolId = spoolId;
    }

    public EzSpoolReport(long spoolId, String reportName, String refId, Date businessDate, String createdBy, Date createdDate, String companyCode, String type) {
        this.spoolId = spoolId;
        this.reportName = reportName;
        this.refId = refId;
        this.businessDate = businessDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.companyCode = companyCode;
        this.type = type;
    }

    public long getSpoolId() {
        return spoolId;
    }

    public void setSpoolId(long spoolId) {
        this.spoolId = spoolId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getInitialBy() {
        return initialBy;
    }

    public void setInitialBy(String initialBy) {
        this.initialBy = initialBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getByteReport() {
        return byteReport;
    }

    public void setByteReport(byte[] byteReport) {
        this.byteReport = byteReport;
    }
}
