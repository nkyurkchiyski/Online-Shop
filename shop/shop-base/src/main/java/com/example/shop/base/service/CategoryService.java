/*
 * CategoryService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dao.CategoryDao;
import com.example.shop.base.model.Category;

import javax.jws.WebService;

@WebService
public interface CategoryService extends GenericService<Category, Long>
{
    Category getByName(String name);

    void setCategoryDao(CategoryDao categoryDao);
}
