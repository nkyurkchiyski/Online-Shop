/*
 * UserDAO.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.dao;


import com.practice.entities.Role;
import com.practice.entities.User;


public interface UserDAO extends GenericDAO<User>
{
    boolean addRole(User user, Role role);


    boolean existsUserRole(User user, Role role);

}
