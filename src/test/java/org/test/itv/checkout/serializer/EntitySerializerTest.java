package org.test.itv.checkout.serializer;

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
public class EntitySerializerTest {
    @Mock
    ObjectMapper mapper;

    @InjectMocks
    EntitySerializer entitySerializer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSerialiser() throws Exception {
        EntitySerializer<Product> result = EntitySerializer.getSerialiser(Product.class);
        Assert.assertEquals(new EntitySerializer<Product>(Product.class).getClass(), result.getClass());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme