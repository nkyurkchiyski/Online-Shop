/*
 * DBDataSource.java
 *
 * created at 2019-09-17 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.utilities;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;


public class DBCPDataSource
{
    private static BasicDataSource dataSource = new BasicDataSource();

    static
    {
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=traineeDB");
        dataSource.setUsername("trainee");
        dataSource.setPassword("123456");
    }


    public static Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }


    private DBCPDataSource()
    {
    }
}
