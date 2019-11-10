/*
 * UserServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import com.example.shop.base.dao.UserDao;
import com.example.shop.base.model.User;
import com.example.shop.base.service.UserService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import javax.inject.Inject;


@Service(classes = UserService.class)
@Bean(id = "userService")
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;


    @Override
    public User create(User entity) {
        final User user = this.userDao.save(entity);
        return user;
    }


    @Override
    public User getById(Integer id) {
        final User user = this.userDao.findById(id);
        return user;
    }


    @Override
    public List<User> getAll() {
        final List<User> users = this.userDao.findAll();
        return users;
    }


    @Override
    public void update(User entity) {
        this.userDao.update(entity);
    }


    @Override
    public void remove(User entity) {
        this.userDao.delete(entity);
    }


    @Override
    public User getByUserName(String username) {
        final User user = this.userDao.findByUserName(username);
        return user;
    }


    @Override
    public User getByEmail(String email) {
        final User user = this.userDao.findByEmail(email);
        return user;
    }


    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
