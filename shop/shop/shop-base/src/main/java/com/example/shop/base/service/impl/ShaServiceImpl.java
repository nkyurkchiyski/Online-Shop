/*
 * BCryptService.java
 *
 * created at 2019-11-11 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;

import com.example.shop.base.service.EncryptionService;


@Service(classes = ShaServiceImpl.class)
@Bean(id = "shaService")
public class ShaServiceImpl implements EncryptionService
{

    @Override
    public String hash(String password)
    {
        MessageDigest md = null;
        String hashed = null;
        try
        {
            md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            hashed = Base64.getEncoder().encodeToString(hash);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return hashed;
    }


    @Override
    public boolean verify(String password, String hashed)
    {
        return this.hash(password).equals(hashed);
    }

}
