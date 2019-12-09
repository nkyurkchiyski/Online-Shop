/*
 * UserService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dto.UserFormDto;


public interface UserService extends GenericService<UserFormDto, Integer>
{
    <T> T getByEmail(String email, Class<T> type);


    <T> T getCart(Integer userId, Class<T> type);


    boolean hasCart(Integer userId);
}
