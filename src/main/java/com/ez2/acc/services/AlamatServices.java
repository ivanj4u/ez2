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

import com.ez2.acc.dao.AlamatDao;
import com.ez2.acc.entity.EzAlamat;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AlamatServices extends AuditTrailServices {
    private static final Logger logger = LoggerFactory.getLogger(AlamatServices.class);

    @Autowired private AlamatDao daoAlamat;

    public List<EzAlamat> queryList(Predicate predicate) {
        List<EzAlamat> list = new ArrayList<>();
        try {
            if (predicate != null) {
                list = daoAlamat.findAll(predicate);
            } else {
                list = daoAlamat.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(Object pojo) {

    }

    @Override
    public void update(Object pojo) {

    }
}
