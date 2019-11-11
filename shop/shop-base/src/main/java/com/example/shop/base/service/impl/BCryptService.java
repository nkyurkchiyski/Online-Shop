/*
 * BCryptService.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import org.mindrot.jbcrypt.BCrypt;

import com.example.shop.base.service.EncryptionService;


public class BCryptService implements EncryptionService
{

    @Override
    public String hash(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    @Override
    public boolean verify(String password)
    {
        final String hashed = this.hash(password);
        return BCrypt.checkpw(password, hashed);
    }

}
