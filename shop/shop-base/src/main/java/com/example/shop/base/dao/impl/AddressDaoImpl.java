/*
 * AddressDaoImpl.java
 *
 * created at 2019-11-07 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.shop.base.dao.AddressDao;
import com.example.shop.base.model.Address;
import org.apache.aries.blueprint.annotation.bean.Bean;

@Transactional
@Bean(id = "addressDao")
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public Address save(Address entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public Address update(Address entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public void delete(Address entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public List<Address> findAll() {
        this.entityManager.getTransaction().begin();
        final List<Address> addresses = this.entityManager.createQuery("SELECT a FROM Address a", Address.class)//
                .getResultList();
        this.entityManager.getTransaction().commit();
        return addresses;
    }


    @Override
    public Address findById(Integer id) {
        this.entityManager.getTransaction().begin();
        final Address address = this.entityManager.find(Address.class, id);
        this.entityManager.getTransaction().commit();
        return address;
    }


    @Override
    public Integer size() {
        this.entityManager.getTransaction().begin();
        final Integer size = this.entityManager.createQuery("SELECT count(a) FROM Address a", Integer.class)//
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }

}
