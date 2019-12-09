/*
 * AuthenticationServiceImpl.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import javax.inject.Inject;

import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dto.UserFormDto;
import com.example.shop.base.service.UserService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import com.example.shop.base.dto.UserViewDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.model.User;
import com.example.shop.base.service.AuthenticationService;
import com.example.shop.base.service.EncryptionService;


@Service(classes = AuthenticationService.class)
@Bean(id = "authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Inject
    private UserService userService;

    @Inject
    private EncryptionService encryptionService;

    private ModelMapper mapper;


    public AuthenticationServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public UserViewDto register(UserFormDto dto)
    {
        return this.userService.create(dto, UserViewDto.class);
    }


    @Override
    public UserViewDto login(UserLoginDto dto)
    {
        final User user = this.userService.getByEmail(dto.getEmail(), User.class);

        if (!this.encryptionService.verify(dto.getPassword(), user.getPassword()))
        {
            throw new IllegalArgumentException(ErrorMessage.USER_LOGIN_UNSUCCESSFUL);
        }

        final UserViewDto userViewDto = this.mapper.map(user, UserViewDto.class);
        userViewDto.setAdmin(user.isAdmin());
        return userViewDto;

    }
}
