/*
 * AuthenticationServiceImpl.java
 *
 * created at 2019-11-12 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.Arrays;
import java.util.HashSet;

import javax.inject.Inject;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.dao.UserDao;
import com.example.shop.base.dto.UserDto;
import com.example.shop.base.dto.UserLoginDto;
import com.example.shop.base.dto.UserRegisterDto;
import com.example.shop.base.model.Role;
import com.example.shop.base.model.User;
import com.example.shop.base.service.AuthenticationService;
import com.example.shop.base.service.EncryptionService;
import com.example.shop.base.service.RoleService;


@Service(classes = AuthenticationService.class)
@Bean(id = "authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Inject
    private UserDao userDao;

    @Inject
    private RoleService roleService;

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
        this.validateUserRegisterDto(dto);
        final String hashed = this.encryptionService.hash(dto.getPassword());
        dto.setPassword(hashed);

        User user = this.mapper.map(dto, User.class);
        user = this.userDao.save(user);

        this.roleService.addUserToRole(user.getId(), "NormalUser");
        return this.mapper.map(user, UserDto.class);
    }


    @Override
    public UserDto login(UserLoginDto dto)
    {
        final User user = this.userDao.findByEmail(dto.getEmail());

        if (user == null)
        {
            throw new IllegalArgumentException("Invalid email!");
        }

        final boolean matched = this.encryptionService.verify(dto.getPassword(), user.getPassword());

        if (!matched)
        {
            throw new IllegalArgumentException("Invalid password!");
        }

        final UserDto userDto = this.mapper.map(user, UserDto.class);
        userDto.setAdmin(user.isAdmin());
        return userDto;
    }


    private void validateUserRegisterDto(final UserRegisterDto dto)
    {
        final boolean exists = this.userDao.findByEmail(dto.getEmail()) != null;

        if (exists)
        {
            throw new IllegalArgumentException("User with the same email already exists!");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword()))
        {
            throw new IllegalArgumentException("Passwords do not match!");
        }
    }

}
