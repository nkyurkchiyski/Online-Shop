/*
 * CategoryDao.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dao;


import com.example.shop.base.model.Category;


public interface CategoryDao extends GenericDao<Category, Long>
{
    Category findByName(String categoryName);
}


