/*
 * User.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.entities;


import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


public class User
{
    private int userId;
    private String userName;
    private String password;
    private Date createdAt;
    private Date lastModifiedAt;
    private byte[] picture;
    private Boolean active;
    private Set<Role> roles;


    public User()
    {
    }


    public User(String userName, String password, byte[] picture)
    {
        this.userName = userName;
        this.password = password;
        this.picture = picture;
    }


    public User(int userId, String userName, String password, Date createdAt, Date lastModifiedAt, byte[] picture, boolean active, Set<Role> roles)
    {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.picture = picture;
        this.active = active;
        this.roles = roles;
    }


    public int getUserId()
    {
        return userId;
    }


    public String getUserName()
    {
        return userName;
    }


    public String getPassword()
    {
        return password;
    }


    public Date getCreatedAt()
    {
        return createdAt;
    }


    public Date getLastModifiedAt()
    {
        return lastModifiedAt;
    }


    public byte[] getPicture()
    {
        return picture;
    }


    public Set<Role> getRoles()
    {
        return roles;
    }


    public Boolean isActive()
    {
        return active;
    }


    public void setActive(Boolean active)
    {
        this.active = active;
    }


    public void setUserId(int userId)
    {
        this.userId = userId;
    }


    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }


    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }


    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        final String rolesString = this.roles.stream() //
                                             .map(n -> n.toString()) //
                                             .collect(Collectors.joining("\n"));
        sb.append(String.format("UserId: %d; UserName: %s; Roles: %s", this.userId, this.userName, rolesString));
        return sb.toString();
    }

}
