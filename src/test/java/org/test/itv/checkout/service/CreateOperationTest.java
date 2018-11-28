package org.test.itv.checkout.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.datasource.MapDBDataSourceAdapter;
import org.test.itv.checkout.dto.CreateTask;
import org.test.itv.checkout.dto.EntityList;
import org.test.itv.checkout.dto.Product;
import org.test.itv.checkout.logging.Logger;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Created on 27/11/18.
 */
public class CreateOperationTest {
    @Mock
    Logger logger;

    @Mock
    DataSourceProvider dataProvider;
    @InjectMocks
    CreateOperation createOperation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testInit() throws Exception {
        createOperation.init();
    }

    @Test
    public void testProcess() throws Exception {
        when(dataProvider.getDataSource(any())).thenReturn(new MapDBDataSourceAdapter("name"));

        createOperation.process(new CreateTask<Product>(Product.class, EntityList.put(new Product("A", "apple", "an Apple's bag", 1.50))));
    }


    @Test
    public void testDispose() throws Exception {
        createOperation.dispose();
    }
}
