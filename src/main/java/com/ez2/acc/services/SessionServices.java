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

import com.ez2.acc.dao.SessionDao;
import com.ez2.acc.entity.EzSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class SessionServices {

    private static final Logger logger = LoggerFactory.getLogger(SessionServices.class);
    @Autowired
    private SessionDao daoSession;

    public void sessionLogin(String username, String sessionId, String ip) throws Exception {
        try {
            EzSession pojo = daoSession.findOne(username);
            Date now = new Date();
            if (pojo == null) {
                pojo = new EzSession();
                pojo.setUserId(username);
                pojo.setCreateBy(username);
                pojo.setCreateDate(now);
                pojo.setVersi(now.getTime());
            } else {
                pojo.setUpdateBy(username);
                pojo.setUpdateDate(now);
                pojo.setVersi(now.getTime());
            }
            pojo.setSessionId(sessionId);
            pojo.setIp(ip);
            daoSession.save(pojo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public boolean sessionCheck(String username, String sessionId) throws Exception {
        boolean valid = true;
        try {
            EzSession pojo = daoSession.findOne(username);
            if (pojo == null) {
                valid = false;
            } else {
                if (!pojo.getSessionId().equals(sessionId))
                    valid = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return valid;
    }
}
