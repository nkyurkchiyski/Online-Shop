package com.example.shop.web.handler;

import com.example.shop.web.controller.Controller;
import com.example.shop.web.util.Endpoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements Handler {

    private Map<String, Map<String, Method>> routingTable;

    public RequestHandler(Class<?> type) {
        this.initializeRoutingTable(type);
    }


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void initializeRoutingTable(Class<?> type) {
        final Method[] methods = type.getDeclaredMethods();
        this.routingTable = new HashMap<String, Map<String, Method>>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Endpoint.class)) {
                final Endpoint endpoint = method.getAnnotation(Endpoint.class);
                final String endpointMethod = endpoint.method();
                final String endpointPath = endpoint.path();

                this.routingTable.putIfAbsent(endpointMethod, new HashMap<String, Method>());
                this.routingTable.get(endpointMethod).put(endpointPath,method);
            }
        }
    }
}
