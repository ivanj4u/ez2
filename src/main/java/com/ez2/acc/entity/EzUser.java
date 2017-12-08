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
@Table(name = "ez_user", schema = "ez")
public class EzUser extends AuditTrail implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "company_code", nullable = false, length = 5)
    private String companyCode;
    @Column(name = "password", length = 50)
    private String password;
    @Column(name = "user_name", length = 200)
    private String name;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "image")
    private String image;
    /**
     * 0 = Tidak Aktif
     * 1 = Aktif
     * <p>
     * 2 = Blokir
     */
    @Column(name = "status", length = 2)
    private String status;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_time")
    private Date startTime;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "login_fail_count", length = 2)
    private Integer loginFailCount;
    @Temporal(TemporalType.DATE)
    @Column(name = "last_login")
    private Date lastLogin;

    public EzUser() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 0 = Tidak Aktif
     * 1 = Aktif
     * 2 = Blokir
     */
    public String getStatus() {
        return status;
    }

    /**
     * 0 = Tidak Aktif
     * 1 = Aktif
     * 2 = Blokir
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getLoginFailCount() {
        return loginFailCount;
    }

    public void setLoginFailCount(Integer loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
