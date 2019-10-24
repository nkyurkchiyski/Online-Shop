/*
 * Activator.java
 *
 * created at 2019-10-24 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.activator;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator
{
    private static final Logger LOGGER = Logger.getLogger(Activator.class);

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



