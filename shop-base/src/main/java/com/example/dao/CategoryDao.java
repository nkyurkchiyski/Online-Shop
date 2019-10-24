/*
 * CategoryDao.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.dao;


import com.example.model.Category;


public interface CategoryDao extends GenericDao<Category, Long>
{
    Category findByName(String categoryName);
}
