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

package com.ez2.acc.dao;

import com.ez2.acc.entity.EzCompany;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyDao extends CrudRepository<EzCompany, String> {

    List<EzCompany> queryEzCompaniesByCompanyCodeEqualsAndNameLike(String companyCode, String name);

    List<EzCompany> queryEzCompaniesByNameLike(String name);

    List<EzCompany> queryEzCompaniesByCompanyCodeEquals(String companyCode);

    @Override
    List<EzCompany> findAll();
}
