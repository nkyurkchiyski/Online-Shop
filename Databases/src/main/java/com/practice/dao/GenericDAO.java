/*
 * BaseDAO.java
 *
 * created at 2019-09-16 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.dao;


public interface GenericDAO<T>
{
    boolean insert(T entity);


    boolean update(T entity);


    boolean delete(T entity);


    boolean exists(final String name);


    T getByName(final String name);

}
