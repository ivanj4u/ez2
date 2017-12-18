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

import com.ez2.acc.dao.PriviledgeDao;
import com.ez2.acc.entity.EzPriviledge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PriviledgeServices {
    private static Logger logger = LoggerFactory.getLogger(PriviledgeServices.class);
    @Autowired
    private PriviledgeDao daoPriviledge;

    public List<EzPriviledge> getGroupPriviledge(String groupId) {
        List<EzPriviledge> list = new ArrayList<>();
        try {
            list = daoPriviledge.findByGroupId(new Long(groupId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public EzPriviledge getMenuPriviledge(String menuId, Long groupId) {
        EzPriviledge priviledge = null;
        try {
            priviledge = daoPriviledge.findByMenuIdAndGroupId(menuId, groupId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return priviledge;
    }

    public void save(EzPriviledge priviledge) {
        try {
            daoPriviledge.save(priviledge);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public void deletePriviledgeGroup(Long groupId) {
        try {
            List<EzPriviledge> list = daoPriviledge.findByGroupId(groupId);
            for (EzPriviledge priviledge : list) {
                delete(priviledge);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public void delete(EzPriviledge priviledge) {
        try {
            daoPriviledge.delete(priviledge);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
