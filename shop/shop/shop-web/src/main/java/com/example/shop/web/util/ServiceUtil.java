package com.example.shop.web.util;


import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.example.shop.web.controller.Controller;


public class ServiceUtil
{
    @SuppressWarnings({"rawtypes"})
    public static <T> T getService(Class clazz, Class<T> type)
    {
        final Bundle currentBundle = FrameworkUtil.getBundle(clazz);
        if (currentBundle == null)
        {
            return null;
        }

        final BundleContext bundleContext = currentBundle.getBundleContext();
        if (bundleContext == null)
        {
            return null;
        }

        final ServiceReference<T> serviceReference = bundleContext.getServiceReference(type);
        if (serviceReference == null)
        {
            return null;
        }

        T service = bundleContext.getService(serviceReference);
        if (service == null)
        {
            return null;
        }

        return service;
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> List<T> getAllServices(Class clazz, Class<T> type) throws InvalidSyntaxException
    {
        final Bundle currentBundle = FrameworkUtil.getBundle(Controller.class);
        if (currentBundle == null)
        {
            return null;
        }

        final BundleContext bundleContext = currentBundle.getBundleContext();
        if (bundleContext == null)
        {
            return null;
        }

        final ServiceReference< ? >[] serviceReferences = bundleContext.getAllServiceReferences(type.getName(), null);

        if (serviceReferences == null)
        {
            return null;
        }

        final List<T> services = new ArrayList<T>();

        for (ServiceReference< ? > ref : serviceReferences)
        {
            services.add((T)bundleContext.getService(ref));
        }

        return services;
    }
}
