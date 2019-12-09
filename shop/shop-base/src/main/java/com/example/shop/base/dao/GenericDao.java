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

import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;


public interface GenericDao<E, PK extends Serializable>
{
    E save(E entity);


    void update(E entity);


    void delete(E entity);


    List<E> findAll();


    List<E> findAllPaginated(int firstResult, int maxResults);


    E findById(PK id) throws NoSuchEntityException, NonUniqueEntityException;


    Long size();

}
