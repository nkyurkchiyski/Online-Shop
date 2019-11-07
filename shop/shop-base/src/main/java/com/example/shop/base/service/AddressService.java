/*
 * AddressService.java
 *
 * created at 2019-11-07 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import javax.jws.WebService;

import com.example.shop.base.dao.AddressDao;
import com.example.shop.base.model.Address;


@WebService
public interface AddressService extends GenericService<Address, Integer>
{
    void setAddressDao(AddressDao addressDao);
}
