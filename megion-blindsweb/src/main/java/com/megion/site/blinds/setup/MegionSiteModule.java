package com.megion.site.blinds.setup;

import info.magnolia.module.ModuleLifecycle;
import info.magnolia.module.ModuleLifecycleContext;
import info.magnolia.module.blossom.module.BlossomModuleSupport;

/**
 * Module class that starts and stops Spring when called by Magnolia.
 */
public class MegionSiteModule extends BlossomModuleSupport implements ModuleLifecycle {

    @Override
    public void start(ModuleLifecycleContext moduleLifecycleContext) {
        initRootWebApplicationContext("classpath:/applicationContext.xml");
        initBlossomDispatcherServlet("blossom", "classpath:/blossom-servlet.xml");
    }

    @Override
    public void stop(ModuleLifecycleContext moduleLifecycleContext) {
        destroyDispatcherServlets();
        closeRootWebApplicationContext();
    }
}
