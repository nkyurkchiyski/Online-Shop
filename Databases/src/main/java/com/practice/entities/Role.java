/*
 * Role.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.entities;


import java.util.Set;
import java.util.stream.Collectors;


public class Role
{
    private int roleId;
    private String roleName;
    private Set<Right> rights;


    public Role()
    {
    }


    public Role(String roleName)
    {
        this.roleName = roleName;
    }


    public Role(int roleId, String roleName, Set<Right> rights)
    {
        this.roleId = roleId;
        this.roleName = roleName;
        this.rights = rights;
    }


    public int getRoleId()
    {
        return roleId;
    }


    public String getRoleName()
    {
        return roleName;
    }


    public Set<Right> getRights()
    {
        return rights;
    }


    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }


    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }


    public void setRights(Set<Right> rights)
    {
        this.rights = rights;
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        final String rightsString = this.rights.stream() //
                                               .map(n -> n.toString()) //
                                               .collect(Collectors.joining("\n"));
        sb.append(String.format("RoleId: %d; RoleName: %s; %nRights: %n%s", this.roleId, this.roleName, rightsString));
        return sb.toString();
    }
}
