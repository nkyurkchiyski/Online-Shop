/*
 * GenericDao.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;

import java.util.List;

public interface GenericDao<E>
{
    E create(E entity);

    E update(E entity);

    E delete(E entity);

    List<E> findAll();

    E findById(int id);

    long size();

}



