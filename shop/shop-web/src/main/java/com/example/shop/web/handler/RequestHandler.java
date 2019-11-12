package com.example.shop.web.handler;


import com.example.shop.web.controller.Controller;
import com.example.shop.web.util.Endpoint;
import com.example.shop.web.util.EndpointUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RequestHandler implements Handler
{

    private Map<String, Map<String, Method>> routingTable;
    private Controller controller;


    public RequestHandler(Controller controller)
    {
        this.controller = controller;
        this.initializeRoutingTable(controller);
    }


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final String method = req.getMethod().toLowerCase();
        final String path = EndpointUtil.getPath(req.getRequestURI(), req.getContextPath());
        final Method controllerMethod = this.routingTable.get(method).get(path);

        try
        {
            controllerMethod.invoke(this.controller, req, resp);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }


    private void initializeRoutingTable(Controller controller)
    {
        final Method[] methods = controller.getClass().getDeclaredMethods();
        this.routingTable = new HashMap<String, Map<String, Method>>();
        for (Method method : methods)
        {
            if (method.isAnnotationPresent(Endpoint.class))
            {
                final Endpoint endpoint = method.getAnnotation(Endpoint.class);
                final String endpointMethod = endpoint.method().toLowerCase();
                final String endpointPath = endpoint.path();

                this.routingTable.putIfAbsent(endpointMethod, new HashMap<String, Method>());
                this.routingTable.get(endpointMethod).put(endpointPath, method);
            }
        }
    }
}
