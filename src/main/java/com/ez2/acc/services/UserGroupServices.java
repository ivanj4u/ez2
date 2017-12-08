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

package com.ez2.acc.services;

import com.ez2.acc.container.JoinUserGroup;
import com.ez2.acc.dao.GroupDao;
import com.ez2.acc.dao.UserDao;
import com.ez2.acc.dao.UserGroupDao;
import com.ez2.acc.entity.EzGroup;
import com.ez2.acc.entity.EzUser;
import com.ez2.acc.entity.EzUserGroup;
import com.ez2.acc.util.ValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserGroupServices {
    private static final Logger logger = LoggerFactory.getLogger(UserGroupServices.class);
    @Autowired
    private UserGroupDao daoUserGroup;
    @Autowired
    private UserDao daoUser;
    @Autowired
    private GroupDao daoGroup;

    public List<EzUserGroup> getUserGroupByGroupId(String groupId) throws Exception {
        List<EzUserGroup> list = new ArrayList<>();
        try {
            if (ValidationHelper.validateValueNotNull(groupId)) {
                list = daoUserGroup.findByGroupId(new Long(groupId));
            } else {
                list = daoUserGroup.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public List<EzUserGroup> getUserGroupByUser(String userId) throws Exception {
        List<EzUserGroup> list = new ArrayList<>();
        try {
            if (ValidationHelper.validateValueNotNull(userId)) {
                list = daoUserGroup.findByUserId(userId);
            } else {
                list = daoUserGroup.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public List<EzUserGroup> getUserGroup(String groupId, String userId) throws Exception {
        List<EzUserGroup> list = new ArrayList<>();
        try {
            if (ValidationHelper.validateValueNotNull(userId) && ValidationHelper.validateValueNotNull(groupId)) {
                list = daoUserGroup.findByGroupIdAndUserId(new Long(groupId), userId);
            } else if (ValidationHelper.validateValueNotNull(userId)) {
                list = daoUserGroup.findByUserId(userId);
            } else if (ValidationHelper.validateValueNotNull(groupId)) {
                list = daoUserGroup.findByGroupId(new Long(groupId));
            } else {
                list = daoUserGroup.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public List<JoinUserGroup> queryList(String groupId, String userId) throws Exception {
        List<JoinUserGroup> list = new ArrayList<>();
        try {
            List<EzUserGroup> userGroups = getUserGroup(groupId, userId);
            for (EzUserGroup userGroup : userGroups) {
                EzUser user = daoUser.findOne(userGroup.getUserId());
                EzGroup group = daoGroup.findOne(userGroup.getGroupId());
                list.add(new JoinUserGroup(group, user, userGroup));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public void save(EzUserGroup userGroup) throws Exception {
        try {
            daoUserGroup.save(userGroup);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public void delete(EzUserGroup userGroup) throws Exception {
        try {
            daoUserGroup.delete(userGroup);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
