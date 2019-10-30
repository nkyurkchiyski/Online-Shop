/*
 * Product.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "t_Products")
public class Product implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cProductId")
    private Long id;

    @Column(name = "cProductName")
    private String name;

    @Column(name = "cProductQuantity")
    private Integer quantity;

    @Column(name = "cProductPrice")
    private BigDecimal price;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "t_ProductCategories", //
                    joinColumns = {@JoinColumn(name = "cProductCategoryProductId")}, //
                    inverseJoinColumns = {@JoinColumn(name = "cProductCategoryCategoryId")}, //
                    foreignKey = @ForeignKey(name = "FK_ProductCategories_Products"), //
                    inverseForeignKey = @ForeignKey(name = "FK_ProductCategories_Categories"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductOrder> orders;


    public Product()
    {
    }


    public Long getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }


    public Integer getQuantity()
    {
        return quantity;
    }


    public BigDecimal getPrice()
    {
        return price;
    }


    public Set<Category> getCategories()
    {
        return categories;
    }


    public Set<ProductOrder> getOrders()
    {
        return orders;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }


    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }


    public void setCategories(Set<Category> categories)
    {
        this.categories = categories;
    }


    public void setOrders(Set<ProductOrder> orders)
    {
        this.orders = orders;
    }

}
