/*
 * UserServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.example.shop.base.dao.UserDao;
import com.example.shop.base.dto.UserFormDto;
import com.example.shop.base.model.Role;
import com.example.shop.base.model.User;
import com.example.shop.base.service.EncryptionService;
import com.example.shop.base.service.RoleService;
import com.example.shop.base.service.UserService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;


@Service(classes = UserService.class)
@Bean(id = "userService")
public class UserServiceImpl implements UserService
{
    @Inject
    private UserDao userDao;

    @Inject
    private RoleService roleService;

    @Inject
    private EncryptionService encryptionService;

    private ModelMapper mapper;


    public UserServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public void remove(Integer id)
    {
        final User user = this.getById(id, User.class);
        user.setActive(false);
        this.userDao.delete(user);
    }


    @Override
    public <T> T getByEmail(String email, Class<T> type)
    {
        final User user = this.userDao.findByEmail(email);
        return this.mapper.map(user, type);
    }


    @Override
    public <T> T create(UserFormDto dto, Class<T> type)
    {
        this.validateUserDto(dto);

        final String hashed = this.encryptionService.hash(dto.getPassword());
        dto.setPassword(hashed);

        User user = this.mapper.map(dto, User.class);
        final Role userRole = this.roleService.getByName("NormalUser", Role.class);
        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));

        user = this.userDao.save(user);
        return this.mapper.map(user, type);
    }


    @Override
    public <T> T getById(Integer id, Class<T> type)
    {
        final User user = this.userDao.findById(id);
        return this.mapper.map(user, type);
    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        final List<User> users = this.userDao.findAll();
        return users.stream()
                    .map(source -> this.mapper.map(source, type))//
                    .collect(Collectors.toList());
    }


    @Override
    public <T> T update(UserFormDto dto, Class<T> type)
    {
        final User user = this.mapper.map(dto, User.class);
        this.userDao.update(user);
        return this.mapper.map(user, type);
    }


    private void validateUserDto(final UserFormDto dto)
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
