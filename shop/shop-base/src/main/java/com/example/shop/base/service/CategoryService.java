/*
 * CategoryService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import java.util.List;

import com.example.shop.base.dto.CategoryDto;


public interface CategoryService extends GenericService<CategoryDto, Integer>
{
    <T> T getByName(String name, Class<T> type);


    int getAllPages();


    <T> List<T> getAllPaginated(int pageNumber, Class<T> type);
}
