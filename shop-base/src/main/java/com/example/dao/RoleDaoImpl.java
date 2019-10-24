/*
 * RoleDaoImpl.java
 *
 * created at 2019-10-24 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;


import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.example.model.Role;


@OsgiServiceProvider(classes = {RoleDao.class})
@Singleton
@Transactional
public class RoleDaoImpl implements RoleDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public Role save(Role entity)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void update(Role entity)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void delete(Role entity)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public List<Role> findAll()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Role findById(Long id)
    {
        entityManager.getTransaction().begin();
        final Role role = entityManager.find(Role.class, id);
        entityManager.getTransaction().commit();
        return role;
    }


    @Override
    public long size()
    {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public Role findByName(String roleName)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
