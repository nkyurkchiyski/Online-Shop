/*
 * InvoiceServiceImpl.java
 *
 * created at 2019-11-27 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.example.shop.base.dto.OrderInvoiceDto;
import com.example.shop.base.dto.ProductOrderDto;
import com.example.shop.base.dto.UserInvoiceDto;
import com.example.shop.base.service.InvoiceService;


@Service(classes = InvoiceService.class)
@Bean(id = "invoiceService")
public class InvoiceServiceImpl implements InvoiceService
{
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final float PRODUCT_ID_SPACING = 80f;
    private static final float PRODUCT_NAME_SPACING = 300f;
    private static final float PRODUCT_QUANTITY_SPACING = 100f;


    @Override
    public byte[] generate(OrderInvoiceDto dto) throws IOException, IllegalArgumentException, IllegalAccessException
    {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PDDocument doc = new PDDocument();
        final PDPage page = new PDPage();

        doc.addPage(page);

        final PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.COURIER, 18f);
        contentStream.setLeading(16f);

        this.addTitle(contentStream);
        contentStream.setFont(PDType1Font.COURIER, 14f);
        contentStream.newLineAtOffset(25f, 0f);

        this.addUserInformation(contentStream, dto);

        contentStream.newLine();
        contentStream.newLine();
        this.addOrderInformation(contentStream, dto);

        contentStream.endText();
        contentStream.close();

        doc.save(output);
        doc.close();

        return output.toByteArray();
    }


    private void addTitle(final PDPageContentStream contentStream) throws IOException
    {
        contentStream.newLineAtOffset(250f, 725f);
        this.addLineInDocument(contentStream, "Invoice");
        contentStream.newLineAtOffset(-250f, 0f);
        contentStream.newLine();
        contentStream.newLine();

    }


    private void addOrderInformation(final PDPageContentStream contentStream, final OrderInvoiceDto dto) throws IOException
    {
        this.addLineInDocument(contentStream, "Order:");
        contentStream.newLine();
        this.addLineInDocument(contentStream, String.format("Ordered On - %s", dto.getOrderedOn().format(DATE_FORMATTER)));
        this.addLineInDocument(contentStream, String.format("Approved On - %s", dto.getApprovedOn().format(DATE_FORMATTER)));
        contentStream.newLine();
        this.addLineInDocument(contentStream, "Products:");
        contentStream.newLine();
        this.addProductTableHeader(contentStream);
        addProductTableBody(contentStream, dto);
        contentStream.newLine();
        addTextWithSpacing(contentStream, String.format("Total - $%s", dto.getTotal()), PRODUCT_ID_SPACING + PRODUCT_NAME_SPACING);
    }


    private void addProductTableBody(final PDPageContentStream contentStream, final OrderInvoiceDto dto) throws IOException
    {
        for (ProductOrderDto po : dto.getProducts())
        {
            this.addTextWithSpacing(contentStream, po.getProduct().getId().toString(), PRODUCT_ID_SPACING);
            this.addTextWithSpacing(contentStream, po.getProduct().getName(), PRODUCT_NAME_SPACING);
            this.addTextWithSpacing(contentStream, po.getQuantity().toString(), PRODUCT_QUANTITY_SPACING);
            this.addTextWithSpacing(contentStream, "$" + po.getProduct().getPrice().toString(), 0f);
            contentStream.newLineAtOffset(-(PRODUCT_ID_SPACING + PRODUCT_NAME_SPACING + PRODUCT_QUANTITY_SPACING), 0f);
            contentStream.newLine();

        }
    }


    private void addTextWithSpacing(final PDPageContentStream contentStream, final String text, final float spacing) throws IOException
    {
        contentStream.showText(text);
        contentStream.newLineAtOffset(spacing, 0f);
    }


    private void addProductTableHeader(final PDPageContentStream contentStream) throws IOException
    {
        this.addTextWithSpacing(contentStream, "Id", PRODUCT_ID_SPACING);
        this.addTextWithSpacing(contentStream, "Product Name", PRODUCT_NAME_SPACING);
        this.addTextWithSpacing(contentStream, "Qty", PRODUCT_QUANTITY_SPACING);
        this.addTextWithSpacing(contentStream, "Price", 0f);
        contentStream.newLineAtOffset(-(PRODUCT_ID_SPACING + PRODUCT_NAME_SPACING + PRODUCT_QUANTITY_SPACING), 0f);
        contentStream.newLine();
    }


    private void addUserInformation(final PDPageContentStream contentStream, final OrderInvoiceDto dto) throws IOException, IllegalAccessException
    {
        this.addLineInDocument(contentStream, "Invoice to:");
        contentStream.newLine();
        final Field[] fields = UserInvoiceDto.class.getDeclaredFields();
        for (final Field field : fields)
        {
            field.setAccessible(true);
            addLineInDocument(contentStream, field.get(dto.getUser()));
        }
    }


    private void addLineInDocument(final PDPageContentStream contentStream, final Object obj) throws IOException
    {
        contentStream.showText(obj.toString());
        contentStream.newLine();
    }
}
