/*
 * RoleServlet.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.shop.base.model.Role;
import com.example.shop.base.service.RoleService;


public class RoleServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private transient RoleService roleService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String add = req.getParameter("add");
        String roleId = req.getParameter("roleId");
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();

        if (add != null)
        {
            addRole(writer, name);
        }
        else if (roleId != null && roleId.length() > 0)
        {
            showRole(writer, roleId);
        }
        else
        {
            showRoles(writer);
        }
    }


    private void addRole(PrintWriter writer, String roleName)
    {
        Role role = new Role();
        role.setName(roleName);
        roleService.create(role);
        writer.println("<h1>Role " + role.getName() + " successfully added!</h1>");

    }


    private void showRole(PrintWriter writer, String roleId)
    {
        Role role = roleService.getById(Long.parseLong(roleId));
        if (role != null)
        {
            writer.println("<h1>Role Name: " + role.getName() + " </h1>");
            writer.println("<h2>Role Id: " + role.getId() + "</h2>");
        }
        else
        {
            writer.println("Role with id " + roleId + " not found");
        }

    }


    private void showRoles(PrintWriter writer)
    {
        writer.println("<h1>Roles</h1>");
        List<Role> roles = roleService.getAll();
        for (Role role : roles)
        {
            writer.println("<a href=\"?roleId=" + role.getId() + "\">" + role.getName() + "</a><BR/>");
        }
    }


    public void setRoleService(RoleService roleService)
    {
        this.roleService = roleService;
    }
}
