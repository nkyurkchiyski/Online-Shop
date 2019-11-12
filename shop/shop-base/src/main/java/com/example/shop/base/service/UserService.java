/*
 * UserService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dao.UserDao;
import com.example.shop.base.dto.UserDto;


public interface UserService extends GenericService<UserDto, Integer>
{
    UserDto getByEmail(String email);


    void setUserDao(UserDao userDao);

}
