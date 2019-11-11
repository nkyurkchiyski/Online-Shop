/*
 * RoleDAO.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.dao;


import java.util.Set;

import com.practice.entities.Right;
import com.practice.entities.Role;
import com.practice.entities.User;


public interface RoleDAO extends GenericDAO<Role>
{
    boolean addRight(Role role, Right right);


    boolean existsRoleRight(Role role, Right right);


    Set<Role> getRolesOfUser(User user);

}
