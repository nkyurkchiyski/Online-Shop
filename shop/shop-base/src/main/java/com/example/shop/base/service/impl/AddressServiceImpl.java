/*
 * AddressServiceImpl.java
 *
 * created at 2019-11-07 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.util.List;

import com.example.shop.base.dao.AddressDao;
import com.example.shop.base.model.Address;
import com.example.shop.base.service.AddressService;


public class AddressServiceImpl implements AddressService
{
    private AddressDao addressDao;


    @Override
    public Address create(Address entity)
    {
        return this.addressDao.save(entity);
    }


    @Override
    public Address getById(Integer id)
    {
        return this.addressDao.findById(id);
    }


    @Override
    public List<Address> getAll()
    {
        return this.addressDao.findAll();
    }


    @Override
    public void update(Address entity)
    {
        this.addressDao.update(entity);
    }


    @Override
    public void remove(Address entity)
    {
        this.addressDao.delete(entity);
    }


    @Override
    public void setAddressDao(AddressDao addressDao)
    {
        this.addressDao = addressDao;

    }

}
