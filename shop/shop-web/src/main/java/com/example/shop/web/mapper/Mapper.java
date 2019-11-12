package com.example.shop.web.mapper;

import javax.servlet.http.HttpServletRequest;

public interface Mapper {
    <T> T map(HttpServletRequest request, Class<T> type) ;
}
