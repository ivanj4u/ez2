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

@Entity
@Table(name = "ez_user_group", schema = "ez")
@IdClass(EzUserGroupId.class)
public class EzUserGroup implements Serializable {

    @Id
    @Column(name = "username", length = 20, nullable = false)
    private String username;
    @Id
    @Column(name = "group_id", length = 20, nullable = false)
    private long groupId;

    public EzUserGroup() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
