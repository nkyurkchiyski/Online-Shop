/*
 * UserService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dao.UserDao;
import com.example.shop.base.model.User;


public interface UserService extends GenericService<User, Long>
{
    User getByUserName(String username);


    User getByEmail(String email);


    void setUserDao(UserDao userDao);

}
