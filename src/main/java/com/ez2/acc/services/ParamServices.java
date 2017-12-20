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

import com.ez2.acc.dao.ParamDao;
import com.ez2.acc.entity.EzParam;
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
public class ParamServices extends AuditTrailServices {
    private static final Logger logger = LoggerFactory.getLogger(ParamServices.class);
    @Autowired
    private ParamDao daoParam;

    public List<EzParam> queryList(Predicate predicate) {
        List<EzParam> list = new ArrayList<>();
        try {
            if (predicate != null) {
                list = daoParam.findAll(predicate);
            } else {
                list = daoParam.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public List<EzParam> getList() {
        List<EzParam> list = new ArrayList<>();
        try {
            list = daoParam.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public EzParam getParam(String key) {
        EzParam param = null;
        try {
            param = daoParam.findOne(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return param;
    }

    @Override
    public void save(Object pojo) {
        EzParam param = (EzParam) pojo;
        try {
            saveAudit(param);
            daoParam.save(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Object pojo) {
        EzParam updatedParam = (EzParam) pojo;
        try {
            EzParam param = daoParam.findOne(updatedParam.getKey());
            BeanUtils.copyProperties(updatedParam, param);
            updateAudit(param);
            daoParam.save(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
