/*
 * RoleDao.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;


import com.example.model.Role;


public interface RoleDao extends GenericDao<Role, Long>
{
    Role findByName(String roleName);


}
