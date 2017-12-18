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

import com.ez2.acc.entity.EzMenu;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuDao extends CrudRepository<EzMenu, String>, QueryDslPredicateExecutor<EzMenu> {

    @Query(value = "select menu from EzMenu menu order by menu.menuId asc")
    List<EzMenu> queryEzMenus();

    @Override
    List<EzMenu> findAll(Predicate predicate);

    @Override
    @Query(value = "select menu from EzMenu menu order by menu.parentId asc , menu.noUrut asc")
    List<EzMenu> findAll();

}
