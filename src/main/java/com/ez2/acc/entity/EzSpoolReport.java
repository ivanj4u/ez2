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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ez_spool_report", schema = "ez")
public class EzSpoolReport implements Serializable {

	private long spoolId;
	private String reportName;
	private String refId;
	private Date businessDate;
	private String createdBy;
	private String initialBy;
	private Date createdDate;
	private String branchCode;
	private String type;
	private byte[] byteReport;

	public EzSpoolReport() {
	}

	public EzSpoolReport(long spoolId) {
		this.spoolId = spoolId;
	}

	public EzSpoolReport(long spoolId, String reportName, String refId, Date businessDate, String createdBy, Date createdDate, String branchCode, String type) {
		this.spoolId = spoolId;
		this.reportName = reportName;
		this.refId = refId;
		this.businessDate = businessDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.branchCode = branchCode;
		this.type = type;
	}

	@Id
	@Column(name = "spool_id", nullable = false)
	public long getSpoolId() {
		return this.spoolId;
	}

	public void setSpoolId(long spoolId) {
		this.spoolId = spoolId;
	}

	@Column(name = "report_name", length = 35)
	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Column(name = "ref_id", length = 20)
	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "business_date", length = 13)
	public Date getBusinessDate() {
		return this.businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	@Column(name = "created_by", length = 10)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 29)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "branch_code", length = 5)
	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Column(name = "type", length = 4)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "initial_by", length = 10)
	public String getInitialBy() {
		return initialBy;
	}

	public void setInitialBy(String initialBy) {
		this.initialBy = initialBy;
	}

	@Column(name = "byte_report", length = 10)
	public byte[] getByteReport() {
		return byteReport;
	}

	public void setByteReport(byte[] byteReport) {
		this.byteReport = byteReport;
	}

}
