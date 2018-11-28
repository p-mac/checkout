package org.test.itv.checkout.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.datasource.MapDBDataSourceAdapter;
import org.test.itv.checkout.dto.DeleteTask;
import org.test.itv.checkout.dto.DynaMap;
import org.test.itv.checkout.dto.Product;
import org.test.itv.checkout.logging.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Created on 27/11/18.
 */
public class DeleteOperationTest {
    @Mock
    Logger logger;

    @Mock
    DataSourceProvider dataProvider;
    @InjectMocks
    DeleteOperation deleteOperation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        deleteOperation.init();
    }

    @Test
    public void testProcess() throws Exception {
        when(dataProvider.getDataSource(any())).thenReturn(new MapDBDataSourceAdapter("name"));
        DynaMap params = new DynaMap();
        params.put("id", new ArrayList<String>(Arrays.asList("A")));
        deleteOperation.process(new DeleteTask<Product>(Product.class, params));
    }



    @Test
    public void testDispose() throws Exception {
        deleteOperation.dispose();
    }
}
