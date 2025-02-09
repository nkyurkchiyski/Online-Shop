/*
 * UserDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.User;


public interface UserDao extends GenericDao<User, Integer>
{
    User findByEmail(String email) throws NoSuchEntityException, NonUniqueEntityException;


    boolean existsWithEmail(String email);
}
