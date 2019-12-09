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

import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.Role;
import org.apache.aries.blueprint.annotation.bean.Bean;


@Transactional
@Bean(id = "roleDao")
public class RoleDaoImpl implements RoleDao
{

    @PersistenceContext(unitName = "online-shop")
    EntityManager em;


    @Override
    public Role save(Role entity)
    {
        this.em.getTransaction().begin();
        this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public void update(Role entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
    }


    @Override
    public void delete(Role entity)
    {
        this.em.getTransaction().begin();
        this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
        this.em.getTransaction().commit();
    }


    @Override
    public List<Role> findAll()
    {
        return this.em.createQuery("SELECT r FROM Role r LEFT JOIN FETCH r.users", Role.class)//
                      .getResultList();

    }


    @Override
    public Role findById(Integer id) throws NoSuchEntityException, NonUniqueEntityException
    {

        final List<Role> resultList = this.em.createQuery("SELECT DISTINCT r FROM Role r LEFT JOIN FETCH r.users WHERE r.id = :id ", Role.class)//
                                             .setParameter("id", id)
                                             .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.ROLE_DOES_NOT_EXIST);
        }

        if (resultList.size() > 1)
        {
            throw new NonUniqueEntityException(ErrorMessage.UNIQUE_RESULT_NOT_FOUND);
        }

        return resultList.get(0);
    }


    @Override
    public Long size()
    {
        return this.em.createQuery("SELECT count(r) FROM Role r", Long.class)//
                      .getSingleResult();
    }


    @Override
    public Role findByName(String roleName) throws NoSuchEntityException, NonUniqueEntityException
    {
        final List<Role> resultList = this.em.createQuery("SELECT DISTINCT r FROM Role r WHERE r.name = :name", Role.class)//
                                             .setParameter("name", roleName)
                                             .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.ROLE_DOES_NOT_EXIST);
        }

        if (resultList.size() > 1)
        {
            throw new NonUniqueEntityException(ErrorMessage.UNIQUE_RESULT_NOT_FOUND);
        }

        return resultList.get(0);
    }


    @Override
    public List<Role> findAllPaginated(int firstResult, int maxResults)
    {
        return this.em.createQuery("SELECT r FROM Role r LEFT JOIN FETCH r.users", Role.class)//
                      .setFirstResult(firstResult)
                      .setMaxResults(maxResults)
                      .getResultList();
    }

}
