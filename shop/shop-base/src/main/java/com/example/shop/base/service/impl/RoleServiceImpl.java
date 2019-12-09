/*
 * RoleServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.dto.RoleDto;
import com.example.shop.base.model.Role;
import com.example.shop.base.service.RoleService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;


@Service(classes = RoleService.class)
@Bean(id = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Inject
    private RoleDao roleDao;

    private ModelMapper mapper;


    public RoleServiceImpl()
    {
        this.mapper = new ModelMapper();
    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        final List<Role> roles = this.roleDao.findAll();
        return roles.stream()
                    .map(source -> this.mapper.map(source, type))//
                    .collect(Collectors.toList());
    }


    @Override
    public <T> T getByName(String name, Class<T> type)
    {
        final Role role = this.roleDao.findByName(name);
        return this.mapper.map(role, type);
    }


    @Override
    public <T> T create(RoleDto dto, Class<T> type)
    {
        final Role role = this.mapper.map(dto, Role.class);
        this.roleDao.save(role);
        return this.mapper.map(role, type);
    }


    @Override
    public <T> T getById(Integer id, Class<T> type)
    {
        final Role role = this.roleDao.findById(id);
        return this.mapper.map(role, type);
    }


    @Override
    public <T> T update(RoleDto dto, Class<T> type)
    {
        final Role role = this.mapper.map(dto, Role.class);
        this.roleDao.update(role);
        return this.mapper.map(role, type);
    }


    @Override
    public void remove(Integer id)
    {
        final Role role = this.getById(id, Role.class);
        this.roleDao.delete(role);
    }
}
