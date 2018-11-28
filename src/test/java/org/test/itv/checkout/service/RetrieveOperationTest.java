package org.test.itv.checkout.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.datasource.MapDBDataSourceAdapter;
import org.test.itv.checkout.dto.DynaMap;
import org.test.itv.checkout.dto.Product;
import org.test.itv.checkout.dto.RetrieveTask;
import org.test.itv.checkout.logging.Logger;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Created on 27/11/18.
 */
public class RetrieveOperationTest {
    @Mock
    Logger logger;

    @Mock
    DataSourceProvider dataProvider;
    @InjectMocks
    RetrieveOperation retrieveOperation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        retrieveOperation.init();
    }

    @Test
    public void testProcess() throws Exception {
        when(dataProvider.getDataSource(any())).thenReturn(new MapDBDataSourceAdapter("name"));

        retrieveOperation.process(new RetrieveTask<Product>(Product.class, new DynaMap() {{ put("name", "apple"); }}));
    }



    @Test
    public void testDispose() throws Exception {
        retrieveOperation.dispose();
    }
}
