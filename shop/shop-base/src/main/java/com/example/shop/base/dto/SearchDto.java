/*
 * SearchDto.java
 *
 * created at 2019-11-18 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.math.BigDecimal;


public class SearchDto
{
    private String term;
    private Integer[] categoryIds;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;


    public String getTerm()
    {
        return term;
    }


    public Integer[] getCategoryIds()
    {
        return categoryIds;
    }


    public BigDecimal getMinPrice()
    {
        return minPrice;
    }


    public BigDecimal getMaxPrice()
    {
        return maxPrice;
    }


    public void setTerm(String term)
    {
        this.term = term;
    }


    public void setCategoryIds(Integer[] categoryIds)
    {
        this.categoryIds = categoryIds;
    }


    public void setMinPrice(BigDecimal minPrice)
    {
        this.minPrice = minPrice;
    }


    public void setMaxPrice(BigDecimal maxPrice)
    {
        this.maxPrice = maxPrice;
    }

}
