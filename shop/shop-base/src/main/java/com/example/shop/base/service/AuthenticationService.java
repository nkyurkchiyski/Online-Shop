/*
 * AuthenticationService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.dto.UserFormDto;


public interface AuthenticationService
{
    public UserViewDto register(UserFormDto dto);


    public UserViewDto login(UserLoginDto dto);
}
