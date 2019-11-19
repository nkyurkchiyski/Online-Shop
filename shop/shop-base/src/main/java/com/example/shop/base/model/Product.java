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
import java.time.LocalDateTime;
import java.util.HashSet;
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
    private Integer id;

    @Column(name = "cProductName")
    private String name;

    @Column(name = "cProductDescription", columnDefinition = "varchar(MAX)")
    private String description;

    @Column(name = "cProductImageUrl", columnDefinition = "varchar(MAX)")
    private String imageUrl;

    @Column(name = "cProductQuantity")
    private Integer quantity;

    @Column(name = "cProductPrice")
    private BigDecimal price;

    @Column(name = "cProductAddedOn")
    private LocalDateTime addedOn;

    @Column(name = "cProductViewCount")
    private Integer viewCount;

    @Column(name = "cProductActive")
    private boolean isActive;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "t_ProductCategories", //
                    joinColumns = {@JoinColumn(name = "cProductCategoryProductId")}, //
                    inverseJoinColumns = {@JoinColumn(name = "cProductCategoryCategoryId")}, //
                    foreignKey = @ForeignKey(name = "FK_ProductCategories_Products"), //
                    inverseForeignKey = @ForeignKey(name = "FK_ProductCategories_Categories"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<ProductOrder> orders = new HashSet<ProductOrder>();


    public Product()
    {
    }


    @PrePersist
    private void prePersist()
    {
        this.addedOn = LocalDateTime.now();
        this.viewCount = 0;
        this.isActive = true;
    }


    public LocalDateTime getAddedOn()
    {
        return addedOn;
    }


    public void setAddedOn(LocalDateTime addedOn)
    {
        this.addedOn = addedOn;
    }


    public boolean isActive()
    {
        return isActive;
    }


    public void setActive(boolean active)
    {
        isActive = active;
    }


    public Integer getViewCount()
    {
        return viewCount;
    }


    public void setViewCount(Integer viewCount)
    {
        this.viewCount = viewCount;
    }


    public Integer getId()
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


    public void setId(Integer id)
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


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    public String getImageUrl()
    {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

}
