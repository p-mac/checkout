package org.test.itv.checkout.logging;

import org.junit.Assert;
import org.junit.Test;

import java.io.PrintStream;

/**
 * Created on 27/11/18.
 */
public class LogManagerTest {

    @Test
    public void testGetOutput() throws Exception {
        PrintStream result = LogManager.getOutput();
        Assert.assertEquals(System.out, result);
    }

    @Test
    public void testGetLogger() throws Exception {
        Logger result = LogManager.getLogger(LogManagerTest.class);
        Assert.assertEquals(LogManagerTest.class, result.getObjectClass());
    }
}
