/*
 * StartUp.java
 *
 * created at 2019-10-23 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.example.entity.Role;

//FOR TESTING PURPOSES ONLY!!!
public class StartUp
{

    public static void main(String[] args)
    {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Role admin = new Role();
        admin.setName("Admin");

        entityManager.persist(admin);
        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public static EntityManager getEntityManager()
    {
        return Persistence.createEntityManagerFactory("online-shop").createEntityManager();
    }

}
