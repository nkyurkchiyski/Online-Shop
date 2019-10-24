/*
 * StartUp.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example;


import com.example.dao.RoleDao;
import com.example.dao.RoleDaoImpl;
import com.example.model.Role;

//FOR TESTING PURPOSES ONLY!!!
public class StartUp
{

    public static void main(String[] args)
    {
        RoleDao roleDao = new RoleDaoImpl();
        Role role = roleDao.findById(1l);
    }

}
