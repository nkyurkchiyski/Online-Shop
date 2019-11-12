/*
 * RoleServiceImpl.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.dto.RoleDto;
import com.example.shop.base.model.Role;
import com.example.shop.base.service.RoleService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import javax.inject.Inject;


@Service(classes = RoleService.class)
@Bean(id = "roleService")
public class RoleServiceImpl implements RoleService {
    @Inject
    private RoleDao roleDao;

    @Override
    public RoleDto create(RoleDto dto)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoleDto getById(Integer id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RoleDto> getAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoleDto update(RoleDto dto)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(RoleDto dto)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public RoleDto getByName(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRoleDao(RoleDao roleDao)
    {
        // TODO Auto-generated method stub

    }

}
