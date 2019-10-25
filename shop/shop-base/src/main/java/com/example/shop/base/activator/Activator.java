/*
 * Activator.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.activator;


import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.example.shop.base.dao.RoleDao;


public class Activator implements BundleActivator
{
    private static final Logger LOGGER = Logger.getLogger(Activator.class);
    private ServiceRegistration<RoleDao> daoregistration;

    @Override
    public void start(BundleContext context) throws Exception
    {
        LOGGER.info("Base bundle started!");
    }


    @Override
    public void stop(BundleContext context) throws Exception
    {
        LOGGER.info("Base bundle stopped!");
    }

}
