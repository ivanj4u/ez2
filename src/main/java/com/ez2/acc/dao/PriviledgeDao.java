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

import com.ez2.acc.entity.EzPriviledge;
import com.ez2.acc.entity.EzPriviledgeId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriviledgeDao extends CrudRepository<EzPriviledge, EzPriviledgeId> {

    List<EzPriviledge> findByGroupId(Long groupId);

    EzPriviledge findByMenuIdAndGroupId(String menuId, Long groupId);
}
