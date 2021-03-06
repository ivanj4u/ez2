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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ez_coa_master", schema = "ez")
public class EzCoaMaster extends AuditTrail implements Serializable {

	@Id
	@Column(name = "no_coa", nullable = false, length = 5)
	private String noCoa;
	@Column(name = "description", length = 200)
	private String description;
	@Column(name = "lvl")
	private int lvl;
	@Column(name = "parent_coa", length = 5)
	private String parentCoa;
	@Column(name = "groups", length = 1)
	private String groups;

	public EzCoaMaster() {
	}

	public EzCoaMaster(String noCoa) {
		this.noCoa = noCoa;
	}

	public EzCoaMaster(String noCoa, String description, int lvl, String parentCoa, String groups) {
		this.noCoa = noCoa;
		this.description = description;
		this.lvl = lvl;
		this.parentCoa = parentCoa;
		this.groups = groups;
	}

	public String getNoCoa() {
		return this.noCoa;
	}

	public void setNoCoa(String noCoa) {
		this.noCoa = noCoa;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLvl() {
		return this.lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getParentCoa() {
		return this.parentCoa;
	}

	public void setParentCoa(String parentCoa) {
		this.parentCoa = parentCoa;
	}

	public String getGroups() {
		return this.groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

}
