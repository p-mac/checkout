package org.test.itv.checkout.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.dto.Product;

/**
 * Created on 27/11/18.
 */
public class ClassLoadUtilTest {
    @Mock
    ObjectMapper mapper;
    @InjectMocks
    ClassLoadUtil classLoadUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetObject() throws Exception {

        Product result = ClassLoadUtil.getObject("org.test.itv.checkout.dto.Product");
        Assert.assertEquals(Product.class, result.getClass());
    }

    @Test
    public void testGetObjectClass() throws Exception {
        Class<Product> result = ClassLoadUtil.getObjectClass("org.test.itv.checkout.dto.Product");
        Assert.assertEquals(Product.class, result);
    }
}
