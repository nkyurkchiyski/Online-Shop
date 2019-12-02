package com.example.shop.web.handler;


import com.example.shop.web.controller.*;
import com.example.shop.web.annotation.Endpoint;
import com.example.shop.web.util.EndpointUtil;
import com.example.shop.web.util.ServiceUtil;
import com.example.shop.web.annotation.WebController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.osgi.framework.InvalidSyntaxException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestHandler implements Handler
{
    private static RequestHandler INSTANCE;
    private static final Logger LOGGER = Logger.getLogger(RequestHandler.class);

    private Map<String, Map<String, Method>> routingTable = new HashMap<>();
    private Map<String, Controller> controllers = new HashMap<>();


    private RequestHandler()
    {
        this.initializeControllers();
        this.initializeRoutingTable();
    }


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp)
    {
        String fullPath = EndpointUtil.getPath(req.getRequestURI(), req.getContextPath());
        final String controllerPath = EndpointUtil.getControllerPath(fullPath);
        final String method = req.getMethod().toLowerCase();

        Controller controller = this.controllers.get(controllerPath);
        final Method controllerMethod = this.routingTable.get(method).get(fullPath);

        try
        {
            controllerMethod.invoke(controller, req, resp);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }


    public static synchronized RequestHandler getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RequestHandler();
        }
        return INSTANCE;
    }


    private void initializeRoutingTable()
    {
        for (final Map.Entry<String, Controller> entry : this.controllers.entrySet())
        {
            final Method[] methods = entry.getValue().getClass().getDeclaredMethods();
            for (final Method method : methods)
            {
                if (method.isAnnotationPresent(Endpoint.class))
                {
                    final Endpoint endpoint = method.getAnnotation(Endpoint.class);
                    final String endpointMethod = endpoint.method().toLowerCase();
                    final String[] endpointUrls = endpoint.urls();

                    this.routingTable.putIfAbsent(endpointMethod, new HashMap<>());
                    for (final String url : endpointUrls)
                    {
                        this.routingTable.get(endpointMethod).put(url, method);
                    }
                }
            }
        }
    }


    private void initializeControllers()
    {
        try
        {
            final List<Controller> controllers = ServiceUtil.getAllServices(Controller.class, Controller.class);
            for (final Controller controller : controllers)
            {
                this.addController(controller);
            }
        }
        catch (IllegalAccessException | InstantiationException | InvalidSyntaxException e)
        {
            LOGGER.error(e.getMessage());
        }
    }


    private void addController(Controller controller) throws IllegalAccessException, InstantiationException
    {
        this.controllers.put(getControllerPath(controller.getClass()), controller);
    }


    private String getControllerPath(Class< ? > type)
    {
        final WebController annotation = type.getAnnotation(WebController.class);
        return annotation.path();
    }
}
