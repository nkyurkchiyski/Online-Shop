/*
 * BCryptService.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.mindrot.jbcrypt.BCrypt;

import com.example.shop.base.service.EncryptionService;


@Service(classes = BCryptServiceImpl.class)
@Bean(id = "bcryptService")
public class BCryptServiceImpl implements EncryptionService
{

    @Override
    public String hash(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    @Override
    public boolean verify(String password, String hashed)
    {
        return BCrypt.checkpw(password, hashed);
    }

}
