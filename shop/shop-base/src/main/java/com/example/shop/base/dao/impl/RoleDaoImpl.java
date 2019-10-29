/*
 * RoleDaoImpl.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.model.Role;


public class RoleDaoImpl implements RoleDao
{

    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public Role save(Role entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public void update(Role entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public void delete(Role entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public List<Role> findAll()
    {
        this.entityManager.getTransaction().begin();
        List<Role> roles = this.entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        this.entityManager.getTransaction().commit();
        return roles;
    }


    @Override
    public Role findById(Long id)
    {
        this.entityManager.getTransaction().begin();
        Role role = this.entityManager.find(Role.class, id);
        this.entityManager.getTransaction().commit();
        return role;
    }


    @Override
    public long size()
    {
        this.entityManager.getTransaction().begin();
        Long size = this.entityManager.createQuery("SELECT count(r) FROM Role r", Long.class).getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }


    @Override
    public Role findByName(String roleName)
    {
        this.entityManager.getTransaction().begin();
        Role role = this.entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class).setParameter("name", roleName).getSingleResult();
        this.entityManager.getTransaction().commit();
        return role;
    }

}
