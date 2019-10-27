/*
 * RoleDaoImpl.java
 *
 * created at 2019-10-24 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;


import java.util.List;
import javax.persistence.EntityManager;

import com.example.model.Role;

public class RoleDaoImpl implements RoleDao {
    private EntityManager entityManager;


    @Override
    public Role save(Role entity) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void update(Role entity) {
        // TODO Auto-generated method stub

    }


    @Override
    public void delete(Role entity) {
        // TODO Auto-generated method stub

    }


    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("select d from Role d", Role.class).getResultList();
    }


    @Override
    public Role findById(Long id) {
        return null;
    }


    @Override
    public long size() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public Role findByName(String roleName) {
        // TODO Auto-generated method stub
        return null;
    }
}
