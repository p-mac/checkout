package org.test.itv.checkout.service;

import org.test.itv.checkout.dto.*;

/**
 * Basket Add operation to performs the add based on a given item to add into the basket
 * it relays on the super class BasketUpdateOperation to calculate the partials and the total amounts
 * for the whole basket based on the update
 */
public class BasketAddOperation extends BasketUpdateOperation {

    public BasketAddOperation() {
        super();
    }

    public BasketAddOperation(Class<Basket> entityClass) {
        super(entityClass);
    }

    /**
     * main process method targeted to execute the Operation
     * @param task
     */
    @Override
    public void process(UpdateTask<Basket> task) {
        String productId = task.getParams().getString("productid");
        String baskedId = task.getParams().getString("id");

        Basket basket = retrieveBasket(baskedId);
        if (basket != null) {
            basket.addItem(productId);
        }
        super.process(task);
    }

    /**
     * facility method to retrieve a Basket based on its id
     * @param basketId
     * @return Basket
     */
    protected Basket retrieveBasket(String basketId) {
        RetrieveTask<Basket> task = new RetrieveTask<Basket>(Basket.class, DynaMap.set("id", basketId));
        RetrieveOperation<Basket> operation = new RetrieveOperation<>(Basket.class);
        operation.process(task);
        return (!task.getEntities().isEmpty()) ? task.getEntities().get(0) : null;
    }


}
