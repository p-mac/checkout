package org.test.itv.checkout.datasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mapdb.DB;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.dto.DynaMap;
import org.test.itv.checkout.dto.EntityList;
import org.test.itv.checkout.dto.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 27/11/18.
 */
public class MapDBDataSourceAdapterTest {
    @Mock
    DB db;
    @InjectMocks
    MapDBDataSourceAdapter mapDBDataSourceAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        DataSourceAdapter result = mapDBDataSourceAdapter.init();
        Assert.assertEquals(new MapDBDataSourceAdapter("name").getClass(), result.getClass());
    }

    @Test
    public void testRetrieve() throws Exception {
        mapDBDataSourceAdapter.init();
        List<Product> result = mapDBDataSourceAdapter.retrieve(new ArrayList<>(Arrays.asList("A")), Product.class);
        Assert.assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testRetrieve2() throws Exception {
        mapDBDataSourceAdapter.init();
        List<Product> result = mapDBDataSourceAdapter.retrieve(new DynaMap(), Product.class);
        Assert.assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testCreate() throws Exception {
        mapDBDataSourceAdapter.init();
        Product product = new Product("A", "Apple", 1.5);
        List<Product> result = mapDBDataSourceAdapter.create(EntityList.put(product), Product.class);
        Assert.assertEquals(EntityList.put(product), result);
    }

    @Test
    public void testUpdate() throws Exception {
        mapDBDataSourceAdapter.init();
        Product product = new Product("A", "Apple", 1.5);
        List<Product> result = mapDBDataSourceAdapter.update(EntityList.put(product), Product.class);
        Assert.assertEquals(EntityList.put(product), result);
    }

    @Test
    public void testDelete() throws Exception {
        mapDBDataSourceAdapter.init();
        mapDBDataSourceAdapter.delete(new ArrayList<>(Arrays.asList("A")), Product.class);
    }

    @Test
    public void testDispose() throws Exception {
        mapDBDataSourceAdapter.dispose();
    }
}
