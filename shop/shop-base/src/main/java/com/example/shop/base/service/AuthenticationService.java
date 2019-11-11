/*
 * AuthenticationService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dto.UserDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.dto.UserRegisterDto;


public interface AuthenticationService
{
    public UserDto register(UserRegisterDto dto);


    public UserDto login(UserLoginDto dto);
}
