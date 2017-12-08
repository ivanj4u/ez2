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

import com.ez2.acc.entity.EzKabupaten;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KabupatenDao extends CrudRepository<EzKabupaten, String>, QueryDslPredicateExecutor<EzKabupaten> {

    @Override
    List<EzKabupaten> findAll();

    @Override
    List<EzKabupaten> findAll(Predicate predicate);
}
