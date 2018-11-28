package org.test.itv.checkout.logging;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.dto.Product;

import java.io.PrintStream;

/**
 * Created on 27/11/18.
 */
public class LoggerTest {

    @Mock
    PrintStream output;
    @InjectMocks
    Logger logger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        logger.setObjectClass(Product.class);
        logger.setOutput(System.out);
    }

    @Test
    public void testError() throws Exception {
        logger.error("message", new Throwable("error message"));
    }

    @Test
    public void testDebug() throws Exception {
        logger.debug("message", null);
    }

    @Test
    public void testTrace() throws Exception {
        logger.trace("message", null);
    }

    @Test
    public void testInfo() throws Exception {
        logger.info("message");
    }
}
