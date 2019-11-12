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
    E create(E dto);


    E getById(PK id);


    List<E> getAll();


    E update(E dto);


    void remove(E dto);

}
