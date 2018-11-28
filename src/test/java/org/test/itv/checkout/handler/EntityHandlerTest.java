package org.test.itv.checkout.handler;

import org.junit.Assert;
import org.junit.Test;
import org.test.itv.checkout.dto.EntityList;
import org.test.itv.checkout.dto.Product;

/**
 * Created on 27/11/18.
 */
public class EntityHandlerTest {

    EntityHandler<Product> entityHandler = new EntityHandler<>(Product.class);

    @Test
    public void testHandleToken() throws Exception {
        Product product = new Product("A", "Apple", 1.50);
        entityHandler.handleToken(EntityList.put(product));
        Assert.assertNotNull(product.getToken());
    }

    @Test
    public void testHandleToken2() throws Exception {
        Product product = new Product("A", "Apple", 1.50);
        entityHandler.handleToken(product);
        Assert.assertNotNull(product.getToken());
    }

    @Test
    public void testGetHandler() throws Exception {
        EntityHandler<Product> result = EntityHandler.getHandler(Product.class);
        Assert.assertEquals(new EntityHandler<Product>(Product.class).getEntityClass(), result.getEntityClass());
    }
}
