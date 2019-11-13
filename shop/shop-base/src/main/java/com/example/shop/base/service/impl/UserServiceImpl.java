/*
 * UserServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.example.shop.base.dao.UserDao;
import com.example.shop.base.dto.UserDto;
import com.example.shop.base.model.User;
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

    private ModelMapper mapper;


    public UserServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }


    @Override
    public void remove(UserDto entity)
    {
        final User user = this.mapper.map(entity, User.class);
        user.setActive(false);
        this.userDao.delete(user);
    }


    @Override
    public UserDto getByEmail(String email)
    {
        final User user = this.userDao.findByEmail(email);
        return this.mapper.map(user, UserDto.class);
    }


    @Override
    public <T> T create(UserDto dto, Class<T> type)
    {
        final User user = this.mapper.map(dto, User.class);
        this.userDao.save(user);
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
    public <T> T update(UserDto dto, Class<T> type)
    {
        final User user = this.mapper.map(dto, User.class);
        this.userDao.update(user);
        return this.mapper.map(user, type);
    }

}
