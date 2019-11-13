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
import com.example.shop.base.dto.AddressDto;
import com.example.shop.base.service.AddressService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import javax.inject.Inject;


@Service(classes = AddressService.class)
@Bean(id = "addressService")
public class AddressServiceImpl implements AddressService
{
    @Inject
    private AddressDao addressDao;


    @Override
    public <T> T create(AddressDto dto, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <T> T getById(Integer id, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <T> List<T> getAll(Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <T> T update(AddressDto dto, Class<T> type)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void remove(AddressDto dto)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void setAddressDao(AddressDao addressDao)
    {
        this.addressDao = addressDao;
    }

}
