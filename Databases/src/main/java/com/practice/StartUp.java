/*
 * StartUp.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice;


import org.apache.log4j.Logger;

import com.practice.entities.*;
import com.practice.exceptions.NoSuchRightException;
import com.practice.exceptions.NoSuchRoleException;
import com.practice.exceptions.NoSuchUserException;
import com.practice.exceptions.UserNameAlreadyExistsException;
import com.practice.services.*;


public class StartUp
{

    private static final Logger LOGGER = Logger.getLogger(StartUp.class);


    public static void main(String[] args)
    {
        executeOperations();
    }


    private static void executeOperations()
    {
        try
        {
            IUserService userService = new UserService();
            IRoleService roleService = new RoleService();
            IRightService rightService = new RightService();

            DBService service = new MssqlDBService(userService, roleService, rightService);

            populateDataBase(userService, rightService, roleService);
            User admin = service.findUser("adminUser");
            admin.setUserName("adminUserName");

            service.updateUser(admin);

            populateDataBase(userService, rightService, roleService);
            Role role = service.findRole("admin");
            service.deleteRole(role);

            populateDataBase(userService, rightService, roleService);

            final Right findRight = service.findRight("find");
            final Right createRight = service.findRight("create");
            final Right updateRight = service.findRight("update");
            final Right deleteRight = service.findRight("delete");

            service.deleteRight(findRight);
            service.deleteRight(createRight);
            service.deleteRight(updateRight);
            service.deleteRight(deleteRight);

            final Role adminRole = service.findRole("admin");
            final Role userRole = service.findRole("user");
            service.deleteRole(adminRole);
            service.deleteRole(userRole);

            final User adminUser = service.findUser("adminUser");
            final User regularUser = service.findUser("regularUser");
            final User adminUser2 = service.findUser("adminUserName");

            service.deleteUser(adminUser);
            service.deleteUser(regularUser);
            service.deleteUser(adminUser2);
        }
        catch (UserNameAlreadyExistsException | NoSuchRoleException | NoSuchRightException | NoSuchUserException e)
        {
            LOGGER.error(e.getMessage());
        }

    }


    private static void populateDataBase(IUserService userService, IRightService rightService, IRoleService roleService)
    {
        final byte[] adminUserPicture = new byte[5];
        final byte[] regularUserPicture = new byte[5];

        final Right findRight = rightService.seed("find");
        final Right createRight = rightService.seed("create");
        final Right updateRight = rightService.seed("update");
        final Right deleteRight = rightService.seed("delete");

        final Role adminRole = roleService.seed("admin");
        final Role userRole = roleService.seed("user");

        roleService.seedRoleRight(adminRole, findRight);
        roleService.seedRoleRight(adminRole, createRight);
        roleService.seedRoleRight(adminRole, updateRight);
        roleService.seedRoleRight(adminRole, deleteRight);

        roleService.seedRoleRight(userRole, findRight);

        final User adminUser = userService.seed("adminUser", "adminUserPassword", adminUserPicture);
        final User regularUser = userService.seed("regularUser", "regularUserPassword", regularUserPicture);

        userService.seedUserRole(adminUser, adminRole);
        userService.seedUserRole(regularUser, userRole);

    }
}
