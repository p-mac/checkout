package org.test.itv.checkout.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.dto.Basket;
import org.test.itv.checkout.dto.DynaMap;
import org.test.itv.checkout.dto.UpdateTask;
import org.test.itv.checkout.logging.Logger;

/**
 * Created on 27/11/18.
 */
public class BasketAddOperationTest {
    @Mock
    Logger logger;

    @Mock
    DataSourceProvider dataProvider;
    @InjectMocks
    BasketAddOperation basketAddOperation;

    Basket basket = new Basket("12345", "dummy");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        basketAddOperation.init();
    }

    @Test
    public void testProcess() throws Exception {
        basketAddOperation.process(new UpdateTask<Basket>(Basket.class, DynaMap.set("id", "12345").add("productid", "A")));
    }

    @Test
    public void testRetrieveBasket() throws Exception {
        Basket result = basketAddOperation.retrieveBasket("12345");
        Assert.assertNull(result);
    }

    @Test
    public void testDispose() throws Exception {
        basketAddOperation.dispose();
    }
}
