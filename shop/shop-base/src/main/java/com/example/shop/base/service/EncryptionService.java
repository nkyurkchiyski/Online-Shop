/*
 * EncryptionService.java
 *
 * created at 2019-10-29 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


public interface EncryptionService
{
    public String hash(String password);


    public boolean verify(String password, String hashed);
}
