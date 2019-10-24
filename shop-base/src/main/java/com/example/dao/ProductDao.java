/*
 * ProductDao.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;

import com.example.model.Product;

public interface ProductDao extends GenericDao<Product, Long>
{
    Product findByName(String productName);
}



