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
    public Role create(Role entity) {
        final Role role = this.roleDao.save(entity);
        return role;
    }


    @Override
    public Role getById(Integer id) {
        final Role role = this.roleDao.findById(id);
        return role;
    }


    @Override
    public List<Role> getAll() {
        final List<Role> roles = this.roleDao.findAll();
        return roles;
    }


    @Override
    public void update(Role entity) {
        this.roleDao.update(entity);
    }


    @Override
    public void remove(Role entity) {
        this.roleDao.delete(entity);
    }


    @Override
    public Role getByName(String name) {
        final Role role = this.roleDao.findByName(name);
        return role;
    }


    @Override
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
