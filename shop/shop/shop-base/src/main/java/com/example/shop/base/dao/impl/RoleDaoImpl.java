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
import javax.transaction.Transactional;

import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.model.Role;
import org.apache.aries.blueprint.annotation.bean.Bean;


@Transactional
@Bean(id = "roleDao")
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
        return this.entityManager.createQuery("SELECT r FROM Role r JOIN FETCH r.users", Role.class)//
                                 .getResultList();

    }


    @Override
    public Role findById(Integer id)
    {
        return this.entityManager.find(Role.class, id);
    }


    @Override
    public Long size()
    {
        return this.entityManager.createQuery("SELECT count(r) FROM Role r", Long.class)//
                                 .getSingleResult();

    }


    @Override
    public Role findByName(String roleName)
    {
        return this.entityManager.createQuery("SELECT r FROM Role r JOIN FETCH r.users WHERE r.name = :name ", Role.class)//
                                 .setParameter("name", roleName)
                                 .getSingleResult();
    }


    @Override
    public List<Role> findAllPaginated(int firstResult, int maxResults)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
