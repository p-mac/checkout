package org.test.itv.checkout.config;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** 
* ConfigManager Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 27, 2018</pre> 
* @version 1.0 
*/ 
public class ConfigManagerTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: getInsntance()
    *
    */
    @Test
    public void testGetInsntance() throws Exception {
        Assert.assertNotNull(ConfigManager.getInsntance());
    }

    /**
    *
    * Method: getConfig(String name)
    *
    */
    @Test
    public void testGetConfigName() throws Exception {
        Assert.assertTrue(ConfigManager.getInsntance().getConfig("server").isObject());
    }

    /**
    *
    * Method: getConfig(String name, Class<T> configclass)
    *
    */
    @Test
    public void testGetConfigForNameConfigclass() throws Exception {
        Assert.assertTrue(ConfigManager.getInsntance().getConfig("server", ServerConfig.class).getPort() == 8080);
    }


} 
