/*
 * RoleService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import com.example.shop.base.dao.RoleDao;
import com.example.shop.base.model.Role;

import javax.jws.WebService;


@WebService
public interface RoleService extends GenericService<Role, Long>
{
    Role getByName(String name);

    void setRoleDao(RoleDao roleDao);

}
