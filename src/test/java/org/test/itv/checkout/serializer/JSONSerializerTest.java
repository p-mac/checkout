package org.test.itv.checkout.serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.dto.Product;
import org.test.itv.checkout.util.MapperFactory;

/**
 * Created on 27/11/18.
 */
public class JSONSerializerTest {
    @Mock
    ObjectMapper mapper;
    @InjectMocks
    JSONSerializer jSONSerializer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mapper = MapperFactory.getMapper();
        jSONSerializer = new JSONSerializer();
    }

    @Test
    public void testBind() throws Exception {
        Object object = new Product();
        Product result = jSONSerializer.bind(object, Product.class);
        Assert.assertEquals(new Product().getPrice(), result.getPrice());
    }


    @Test
    public void testBindToNode() throws Exception {
        JsonNode result = jSONSerializer.bindToNode(new Product());
        Assert.assertTrue(result.isObject());
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme