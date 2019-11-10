package com.example.shop.web.util;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ServiceUtil {
    @SuppressWarnings({"rawtypes"})
    public static <T> T getService(Class clazz, Class<T> type) {
        final Bundle currentBundle = FrameworkUtil.getBundle(clazz);
        if (currentBundle == null) {
            return null;
        }

        final BundleContext bundleContext = currentBundle.getBundleContext();
        if (bundleContext == null) {
            return null;
        }

        final ServiceReference<T> serviceReference = bundleContext.getServiceReference(type);
        if (serviceReference == null) {
            return null;
        }

        T service = bundleContext.getService(serviceReference);
        if (service == null) {
            return null;
        }

        return service;
    }
}
