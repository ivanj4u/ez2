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

import com.ez2.acc.dao.GroupDao;
import com.ez2.acc.entity.EzGroup;
import com.querydsl.core.types.Predicate;
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
public class GroupServices extends AuditTrailServices {

    private static final Logger logger = LoggerFactory.getLogger(GroupServices.class);
    @Autowired
    private GroupDao daoGroup;

    public List<EzGroup> queryList(Predicate predicate) {
        List<EzGroup> list = new ArrayList<>();
        try {
            if (predicate != null) {
                list = daoGroup.findAll(predicate);
            } else {
                list = daoGroup.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public List<EzGroup> getAllGroup() {
        List<EzGroup> list = new ArrayList<>();
        try {
            list = daoGroup.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(Object pojo) {
        EzGroup group = (EzGroup) pojo;
        try {
            saveAudit(group);
            daoGroup.save(group);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Object pojo) {
        EzGroup updatedGroup = (EzGroup) pojo;
        try {
            EzGroup group = daoGroup.findOne(updatedGroup.getGroupId());
            BeanUtils.copyProperties(updatedGroup, group);
            updateAudit(group);
            daoGroup.save(group);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
