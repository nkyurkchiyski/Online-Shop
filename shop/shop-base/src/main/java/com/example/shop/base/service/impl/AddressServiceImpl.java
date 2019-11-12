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
import com.example.shop.base.model.Address;
import com.example.shop.base.service.AddressService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import javax.inject.Inject;

@Service(classes = AddressService.class)
@Bean(id = "addressService")
public class AddressServiceImpl implements AddressService {
    @Inject
    private AddressDao addressDao;

    @Override
    public AddressDto create(AddressDto dto)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AddressDto getById(Integer id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AddressDto> getAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AddressDto update(AddressDto dto)
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
        // TODO Auto-generated method stub

    }


}
