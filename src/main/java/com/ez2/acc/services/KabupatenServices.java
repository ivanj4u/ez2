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

import com.ez2.acc.dao.KabupatenDao;
import com.ez2.acc.entity.EzKabupaten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class KabupatenServices extends AuditTrailServices {
    private static final Logger logger = LoggerFactory.getLogger(KabupatenServices.class);

    @Autowired
    private KabupatenDao daoKabupaten;

    public List<EzKabupaten> getAllKabupaten() {
        List<EzKabupaten> list = new ArrayList<>();
        try {
            list = daoKabupaten.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public List<EzKabupaten> queryByKodeProvinsi(String kodeProvinsi) {
        List<EzKabupaten> list = new ArrayList<>();
        try {
            list = daoKabupaten.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(Object pojo) throws Exception {

    }

    @Override
    public void update(Object pojo) throws Exception {

    }
}