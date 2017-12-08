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

import com.ez2.acc.dao.UserDao;
import com.ez2.acc.entity.EzUser;
import com.ez2.acc.util.ValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServices extends AuditTrailServices {
    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);
    @Autowired
    private UserDao daoUser;

    @Override
    public void save(Object pojo) throws Exception {
        EzUser menu = (EzUser) pojo;
        try {
            saveAudit(menu);
            daoUser.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Object pojo) throws Exception {
        EzUser updatedUser = (EzUser) pojo;
        try {
            EzUser user = daoUser.findOne(updatedUser.getUsername());
            BeanUtils.copyProperties(updatedUser, user);
            updateAudit(user);
            daoUser.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public List<EzUser> queryList(String username, String name) throws Exception {
        List<EzUser> list = new ArrayList<>();
        try {
            if (ValidationHelper.validateValueNotNull(username) && ValidationHelper.validateValueNotNull(name)) {
                list = daoUser.queryEzUsersByUsernameEqualsAndNameIsLike(username, ("%" + name + "%"));
            } else if (ValidationHelper.validateValueNotNull(username)) {
                list = daoUser.queryEzUsersByUsernameEquals(username);
            } else if (ValidationHelper.validateValueNotNull(name)) {
                list = daoUser.queryEzUsersByNameLike(("%" + name + "%"));
            } else {
                list = daoUser.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public EzUser getUser(String username) throws Exception {
        EzUser user = null;
        try {
            user = daoUser.findOne(username);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return user;
    }

}
