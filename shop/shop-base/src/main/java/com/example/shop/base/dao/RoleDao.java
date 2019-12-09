/*
 * RoleDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.Role;


public interface RoleDao extends GenericDao<Role, Integer>
{
    Role findByName(String roleName) throws NoSuchEntityException, NonUniqueEntityException;
}
