/*
 * UserDao.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;

import com.example.model.User;

public interface UserDao extends GenericDao<User, Long>
{
    User findByUserName(String userName);

    User findByEmail(String email);
}



