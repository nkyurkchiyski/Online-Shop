/*
 * InvoiceService.java
 *
 * created at 2019-11-27 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service;


import java.io.IOException;

import com.example.shop.base.dto.OrderInvoiceDto;


public interface InvoiceService
{
    byte[] generate(OrderInvoiceDto dto) throws IOException, IllegalArgumentException, IllegalAccessException;
}
