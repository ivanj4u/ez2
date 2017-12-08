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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuDao extends CrudRepository<EzMenu, String> {

    List<EzMenu> queryEzMenusByMenuIdEqualsAndMenuNameLikeAndParentIdEquals(String menuId, String menuName, String parentId);

    List<EzMenu> queryEzMenusByMenuIdEqualsAndMenuNameLike(String menuId, String menuName);

    List<EzMenu> queryEzMenusByMenuNameLikeAndParentIdEquals(String menuName, String parentId);

    List<EzMenu> queryEzMenusByMenuIdEqualsAndParentIdEquals(String menuId, String parentId);

    List<EzMenu> queryEzMenusByMenuNameLike(String menuName);

    List<EzMenu> queryEzMenusByParentIdEquals(String parentId);

    @Query(value = "select menu from EzMenu menu order by menu.menuId asc")
    List<EzMenu> queryEzMenus();

    @Override
    @Query(value = "select menu from EzMenu menu order by menu.parentId asc , menu.noUrut asc")
    List<EzMenu> findAll();

}
