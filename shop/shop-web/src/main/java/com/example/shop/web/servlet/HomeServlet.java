/*
 * HomeServlet.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.web.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.model.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private transient RoleDao roleDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        showRoles(writer);
    }


//    private void showCategories(PrintWriter writer)
//    {
//        writer.println("<h1>Categories</h1>");
//        List<Category> categories = categoryService.getAll();
//        for (Category category : categories)
//        {
//            writer.println("<a" + category.getId() + "\">" + category.getName() + "</a><BR/>");
//        }
//    }

    private void showRoles(PrintWriter writer) {
        writer.println("<h1>Categories</h1>");
        List<Role> categories = roleDao.findAll();
        for (Role category : categories) {
            writer.println("<a" + category.getId() + "\">" + category.getName() + "</a><BR/>");
        }
    }


    public void setRoleDao(RoleDao roleDao) {
        this.roleDao= roleDao;
    }

}
