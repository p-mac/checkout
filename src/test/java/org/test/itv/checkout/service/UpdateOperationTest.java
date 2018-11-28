package org.test.itv.checkout.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.datasource.MapDBDataSourceAdapter;
import org.test.itv.checkout.dto.EntityList;
import org.test.itv.checkout.dto.Product;
import org.test.itv.checkout.dto.UpdateTask;
import org.test.itv.checkout.logging.Logger;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Created on 27/11/18.
 */
public class UpdateOperationTest {
    @Mock
    Logger logger;

    @Mock
    DataSourceProvider dataProvider;
    @InjectMocks
    UpdateOperation updateOperation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        updateOperation.init();
    }

    @Test
    public void testProcess() throws Exception {
        when(dataProvider.getDataSource(any())).thenReturn(new MapDBDataSourceAdapter("name"));

        updateOperation.process(new UpdateTask<Product>(Product.class, EntityList.put(new Product("A", "apple", 1.0))));
    }


    @Test
    public void testDispose() throws Exception {
        updateOperation.dispose();
    }
}
