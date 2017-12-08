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

import com.ez2.acc.entity.EzUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<EzUser, String> {

    EzUser findByUsernameAndPassword(String username, String password);

    @Override
    List<EzUser> findAll();

    List<EzUser> queryEzUsersByNameLike(String name);

    List<EzUser> queryEzUsersByUsernameEquals(String username);

    List<EzUser> queryEzUsersByUsernameEqualsAndNameIsLike(String username, String name);

}
