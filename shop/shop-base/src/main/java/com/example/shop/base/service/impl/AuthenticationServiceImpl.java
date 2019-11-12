/*
 * AuthenticationServiceImpl.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import javax.inject.Inject;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import com.example.shop.base.dao.UserDao;
import com.example.shop.base.dto.UserDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.dto.UserRegisterDto;
import com.example.shop.base.model.User;
import com.example.shop.base.service.AuthenticationService;
import com.example.shop.base.service.EncryptionService;


@Service(classes = AuthenticationService.class)
@Bean(id = "authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Inject
    private UserDao userDao;

    @Inject
    private EncryptionService encryptionService;

    private ModelMapper mapper;


    public AuthenticationServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public UserDto register(UserRegisterDto dto)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public UserDto login(UserLoginDto dto)
    {
        final User user = this.userDao.findByEmail(dto.getEmail());
        // final boolean matched = this.encryptionService.verify(dto.getPassword(), user.getPassword());
        //
        // if (!matched)
        // {
        // return null;
        // }

        final UserDto userDto = this.mapper.map(user, UserDto.class);
        return userDto;
    }

}
