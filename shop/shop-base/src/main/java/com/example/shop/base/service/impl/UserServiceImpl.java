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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.OrderDao;
import com.example.shop.base.dao.UserDao;
import com.example.shop.base.dto.UserFormDto;
import com.example.shop.base.model.Order;
import com.example.shop.base.model.Role;
import com.example.shop.base.model.User;
import com.example.shop.base.service.EncryptionService;
import com.example.shop.base.service.RoleService;
import com.example.shop.base.service.UserService;
import com.example.shop.base.util.StringUtil;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;


@Service(classes = UserService.class)
@Bean(id = "userService")
public class UserServiceImpl implements UserService
{
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=.,<>{}\"':;|])(?=\\S+$).{6,}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^@]+@[^\\.]+\\..+");

    @Inject
    private UserDao userDao;

    @Inject
    private OrderDao orderDao;

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
        this.userDao.update(user);
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
        this.validateCreateUser(dto);
        final String hashed = this.encryptionService.hash(dto.getPassword());
        dto.setPassword(hashed);

        final User user = this.mapper.map(dto, User.class);
        final Role userRole = this.roleService.getByName("NormalUser", Role.class);
        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));

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
    public <T> T update(UserFormDto dto, Class<T> type)
    {
        final User user = this.userDao.findByEmail(dto.getEmail());
        this.validateEditUser(dto);

        final String hashed = this.encryptionService.hash(dto.getPassword());
        dto.setPassword(hashed);

        user.setAddress(dto.getAddress());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        this.userDao.update(user);
        return this.mapper.map(user, type);
    }


    @Override
    public <T> T getCart(Integer userId, Class<T> type)
    {
        final User user = this.getById(userId, User.class);
        final Order cart = this.orderDao.findCartByUserId(user.getId());
        return this.mapper.map(cart, type);
    }


    @Override
    public boolean hasCart(Integer userId)
    {
        final User user = this.getById(userId, User.class);
        return this.orderDao.existsCartWithUserId(user.getId());
    }


    private void validateCreateUser(final UserFormDto dto)
    {
        this.validateFormFields(dto);
        this.validateEmail(dto);
        this.validatePassword(dto);
    }

    private void validateEditUser(final UserFormDto dto)
    {
        this.validateFormFields(dto);
        this.validatePassword(dto);
    }


    private void validateEmail(final UserFormDto dto)
    {
        if (!EMAIL_PATTERN.matcher(dto.getEmail()).matches())
        {
            throw new IllegalArgumentException(ErrorMessage.EMAIL_NOT_VALID);
        }

        if (this.userDao.existsWithEmail(dto.getEmail()))
        {
            throw new IllegalArgumentException(ErrorMessage.USER_ALREADY_EXISTS);
        }
    }


    private void validatePassword(final UserFormDto dto)
    {
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
        {
            throw new IllegalArgumentException(ErrorMessage.PASSWORDS_NOT_MATCHING);
        }

        if (!PASSWORD_PATTERN.matcher(dto.getPassword()).matches())
        {
            throw new IllegalArgumentException(ErrorMessage.PASSWORD_NOT_VALID);
        }
    }


    private void validateFormFields(final UserFormDto dto)
    {
        if (StringUtil.isNullOrEmpty(dto.getAddress())//
            || StringUtil.isNullOrEmpty(dto.getFirstName())//
            || StringUtil.isNullOrEmpty(dto.getLastName())//
            || StringUtil.isNullOrEmpty(dto.getConfirmPassword())//
            || StringUtil.isNullOrEmpty(dto.getPassword()))
        {
            throw new IllegalArgumentException(String.format(ErrorMessage.MANDATORY_FIELDS, "All"));
        }
    }
}
