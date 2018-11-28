package org.test.itv.checkout.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.dto.EntityList;
import org.test.itv.checkout.dto.Product;
import org.test.itv.checkout.logging.Logger;

import java.util.List;

/**
 * Created on 27/11/18.
 */
public class JsonUtilTest {
    @Mock
    Logger logger;
    @InjectMocks
    JsonUtil jsonUtil;

    ObjectMapper mapper = MapperFactory.getMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBindTo() throws Exception {
        Product product = new Product("A", "Apple", 1.50);
        Product result = JsonUtil.bindTo(product, Product.class);
        Assert.assertEquals(product.getPrice(), result.getPrice());
    }

    @Test
    public void testBindToList() throws Exception {
        List<Product> products = EntityList.put(new Product("A", "Apple", 1.50));
        List<Product> result = JsonUtil.bindToList(products, Product.class);
        Assert.assertEquals(products.get(0).getPrice(), result.get(0).getPrice());
    }

    @Test
    public void testBindString() throws Exception {
        Product product = new Product("A", "Apple", 1.50);
        Product result = JsonUtil.bindString(mapper.writeValueAsString(product), Product.class);
        Assert.assertEquals(product.getPrice(), result.getPrice());
    }

    @Test
    public void testBindStringToList() throws Exception {
        List<Product> products = EntityList.put(new Product("A", "Apple", 1.50));
        List<Product> result = JsonUtil.bindStringToList(mapper.writeValueAsString(products), Product.class);
        Assert.assertEquals(products.get(0).getPrice(), result.get(0).getPrice());
    }

}
