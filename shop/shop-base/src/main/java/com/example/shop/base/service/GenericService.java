/*
 * GenericService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import java.io.Serializable;
import java.util.List;


public interface GenericService<E, PK extends Serializable>
{
    <T> T create(E dto, Class<T> type);


    <T> T getById(PK id, Class<T> type);


    <T> List<T> getAll(Class<T> type);


    <T> T update(E dto, Class<T> type);


    void remove(PK id);

}
