/*
 * RoleService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;

import com.example.shop.base.dto.RoleDto;


public interface RoleService extends GenericService<RoleDto, Integer> {
    <T> T getByName(String name, Class<T> type);
}
