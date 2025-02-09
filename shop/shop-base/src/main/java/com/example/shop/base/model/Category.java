/*
 * Category.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;


@Entity
@Table(name = "t_Categories")
public class Category implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cCategoryId")
    private Integer id;

    @Column(name = "cCategoryName")
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Product> products;


    public Category()
    {
    }


    public Integer getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }


    public Set<Product> getProducts()
    {
        return products;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public void setProducts(Set<Product> products)
    {
        this.products = products;
    }


    @PreRemove
    private void removeProductsFromCategory()
    {
        for (final Product pr : this.products)
        {
            pr.getCategories().remove(this);
        }
    }

}
