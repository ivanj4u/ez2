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

import com.ez2.acc.entity.EzKecamatan;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KecamatanDao extends CrudRepository<EzKecamatan, String>, QueryDslPredicateExecutor<EzKecamatan> {

    List<EzKecamatan> queryEzKecamatansByKodeKabupatenEquals(String kodeKabupaten);

    @Override
    List<EzKecamatan> findAll();
}
