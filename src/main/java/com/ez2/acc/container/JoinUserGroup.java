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

package com.ez2.acc.container;

import com.ez2.acc.entity.EzGroup;
import com.ez2.acc.entity.EzUser;
import com.ez2.acc.entity.EzUserGroup;

import java.io.Serializable;

public class JoinUserGroup implements Serializable {
    // Join EzGroup
    private EzGroup group;
    private long group_groupId;
    private String group_groupName;
    // Join EzUser
    private EzUser user;
    private String user_username, user_name;
    // Join EzUserGroup
    private EzUserGroup userGroup;
    private long userGroup_groupId;
    private String userGroup_username;

    public JoinUserGroup(EzGroup group, EzUser user, EzUserGroup userGroup) {
        // Set EzGroup
        this.group = group;
        setGroup_groupId(group.getGroupId());
        setGroup_groupName(group.getGroupName());
        // Set EzUser
        this.user = user;
        setUser_name(user.getName());
        setUser_username(user.getUsername());
        // Set EzUserGroup
        this.userGroup = userGroup;
        setUserGroup_groupId(userGroup.getGroupId());
        setUserGroup_username(userGroup.getUsername());
    }

    public long getGroup_groupId() {
        return group_groupId;
    }

    public void setGroup_groupId(long group_groupId) {
        this.group_groupId = group_groupId;
    }

    public String getGroup_groupName() {
        return group_groupName;
    }

    public void setGroup_groupName(String group_groupName) {
        this.group_groupName = group_groupName;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getUserGroup_groupId() {
        return userGroup_groupId;
    }

    public void setUserGroup_groupId(long userGroup_groupId) {
        this.userGroup_groupId = userGroup_groupId;
    }

    public String getUserGroup_username() {
        return userGroup_username;
    }

    public void setUserGroup_username(String userGroup_username) {
        this.userGroup_username = userGroup_username;
    }
}
