/*
 * GenericDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import java.io.Serializable;
import java.util.List;


public interface GenericDao<E, PK extends Serializable>
{
    E save(E entity);


    void update(E entity);


    void delete(E entity);


    List<E> findAll();


    E findById(PK id);


    long size();

}
