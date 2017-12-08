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

import com.ez2.acc.dao.CompanyDao;
import com.ez2.acc.entity.EzCompany;
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
public class CompanyServices extends AuditTrailServices {
    private static final Logger logger = LoggerFactory.getLogger(CompanyServices.class);
    @Autowired
    private CompanyDao daoCompany;

    @Override
    public void save(Object pojo) throws Exception {
        EzCompany company = (EzCompany) pojo;
        try {
            saveAudit(company);
            daoCompany.save(company);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Object pojo) throws Exception {
        EzCompany updatedCompany = (EzCompany) pojo;
        try {
            EzCompany company = daoCompany.findOne(updatedCompany.getCompanyCode());
            BeanUtils.copyProperties(updatedCompany, company);
            updateAudit(company);
            daoCompany.save(company);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public List<EzCompany> queryList(String companyCode, String name) throws Exception {
        List<EzCompany> list = new ArrayList<>();
        try {
            if (ValidationHelper.validateValueNotNull(companyCode) && ValidationHelper.validateValueNotNull(name)) {
                list = daoCompany.queryEzCompaniesByCompanyCodeEqualsAndNameLike(companyCode, ("%" + name + "%"));
            } else if (ValidationHelper.validateValueNotNull(companyCode)) {
                list = daoCompany.queryEzCompaniesByCompanyCodeEquals(companyCode);
            } else if (ValidationHelper.validateValueNotNull(name)) {
                list = daoCompany.queryEzCompaniesByNameLike(("%" + name + "%"));
            } else {
                list = daoCompany.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }

    public EzCompany getCompany(String companyCode) throws Exception {
        EzCompany company = null;
        try {
            company = daoCompany.findOne(companyCode);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return company;
    }

}
