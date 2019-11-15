/*
 * CategoryProductsDto.java
 *
 * created at 2019-11-15 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.util.Set;


public class CategoryProductsDto
{
    private String name;
    private Set<ProductDto> products;


    public String getName()
    {
        return name;
    }


    public Set<ProductDto> getProducts()
    {
        return products;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public void setProducts(Set<ProductDto> products)
    {
        this.products = products;
    }

}
