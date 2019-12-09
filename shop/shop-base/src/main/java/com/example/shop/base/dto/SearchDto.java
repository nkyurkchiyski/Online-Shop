/*
 * SearchDto.java
 *
 * created at 2019-12-05 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.dto;


import java.math.BigDecimal;
import java.util.List;


public class SearchDto
{
    private List<String> searchTerms;
    private List<Integer> categoryIds;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;


    public SearchDto(List<String> searchTerms, List<Integer> categoryIds, BigDecimal minPrice, BigDecimal maxPrice)
    {
        this.searchTerms = searchTerms;
        this.categoryIds = categoryIds;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }


    public List<String> getSearchTerms()
    {
        return searchTerms;
    }


    public List<Integer> getCategoryIds()
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


    public void setSearchTerms(List<String> searchTerms)
    {
        this.searchTerms = searchTerms;
    }


    public void setCategoryIds(List<Integer> categoryIds)
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
