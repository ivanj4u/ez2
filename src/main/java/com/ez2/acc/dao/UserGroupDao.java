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

import com.ez2.acc.entity.EzUserGroup;
import com.ez2.acc.entity.EzUserGroupId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserGroupDao extends CrudRepository<EzUserGroup, EzUserGroupId> {

    @Override
    List<EzUserGroup> findAll();

    List<EzUserGroup> findByGroupId(Long groupId);

    List<EzUserGroup> findByUserId(String userId);

    List<EzUserGroup> findByGroupIdAndUserId(Long groupId, String userId);

}
