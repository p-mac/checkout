package org.test.itv.checkout.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.dto.*;
import org.test.itv.checkout.logging.Logger;

import java.util.List;

/**
 * Created on 27/11/18.
 */
public class BasketUpdateOperationTest {
    @Mock
    Logger logger;

    @Mock
    DataSourceProvider dataProvider;
    @InjectMocks
    BasketUpdateOperation basketUpdateOperation;

    Basket basket = new Basket("12345", "dummy");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        basketUpdateOperation.init();
    }

    @Test
    public void testProcess() throws Exception {
        basketUpdateOperation.process(new UpdateTask<Basket>(Basket.class, EntityList.put(basket)));
    }

    @Test
    public void testCalculateTotal() throws Exception {
        basketUpdateOperation.calculateTotal(basket);
    }

    @Test
    public void testRetrieveOffers() throws Exception {
        List<Offer> result = basketUpdateOperation.retrieveOffers();
        Assert.assertNotNull(result);
    }

    @Test
    public void testRetrieveProducts() throws Exception {
        List<Product> result = basketUpdateOperation.retrieveProducts();
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetProductsMap() throws Exception {
        DataMap<Product> result = basketUpdateOperation.getProductsMap(basketUpdateOperation.retrieveProducts());
        Assert.assertNotNull(result);
    }

    @Test
    public void testDispose() throws Exception {
        basketUpdateOperation.dispose();
    }
}
