/*
 * Address.java
 *
 * created at 2019-11-07 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "t_Addresses")
public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cAddressId")
    private Integer id;

    @Column(name = "cAddressCountry")
    private String country;

    @Column(name = "cAddressCity")
    private String city;

    @Column(name = "cAddressStreet")
    private String street;

    @Column(name = "cAddressPostCode")
    private String postCode;

    @OneToOne(mappedBy = "address")
    private User user;


    public Address()
    {
    }


    public Integer getId()
    {
        return id;
    }


    public String getCountry()
    {
        return country;
    }


    public String getCity()
    {
        return city;
    }


    public String getStreet()
    {
        return street;
    }


    public String getPostCode()
    {
        return postCode;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }


    public void setCountry(String country)
    {
        this.country = country;
    }


    public void setCity(String city)
    {
        this.city = city;
    }


    public void setStreet(String street)
    {
        this.street = street;
    }


    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }


    public User getUser()
    {
        return user;
    }


    public void setUser(User user)
    {
        this.user = user;
    }


}
