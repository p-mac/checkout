package org.test.itv.checkout.service;

import org.test.itv.checkout.dto.*;

import java.util.List;

/**
 * Basket Update specific Operation to compute the totale/partials amounts for the Basket
 */
public class BasketUpdateOperation extends UpdateOperation<Basket> {

    public BasketUpdateOperation() {
        super();
    }

    public BasketUpdateOperation(Class<Basket> entityClass) {
        super(entityClass);
    }

    /**
     * main process method targeted to execute the Operation
     * @param task
     */
    @Override
    public void process(UpdateTask<Basket> task) {
        if (!task.getEntities().isEmpty()) {
            Basket basket = task.getEntities().get(0);
            calculateTotal(basket);
            super.process(task);
        }
    }

    /**
     * Main computation on the products in the basket,
     * calculating the partial costs for each product
     * based on the items in the basket and any Offer present for that product
     * @param basket
     */
    protected void calculateTotal(Basket basket) {
        List<Offer> offers = retrieveOffers();
        DataMap<Product> products = getProductsMap(retrieveProducts());

        DataMap<Double> partials = new DataMap<>();

        Double total = 0.0;

        basket.getItems().entrySet().forEach(entry -> {
            Double cost = 0.0;
            String prodId = entry.getKey();
            Integer num = entry.getValue();
            Double price = products.get(prodId).getPrice();
            Offer offer = getOffer(offers, prodId);
            if (offer != null) {
                int quantity = offer.getQuantity();
                int group = num/quantity;
                int diff = num-(group*quantity);
                cost = group*offer.getOffer();
                cost += diff*price;
            } else {
                cost = num*price;
            }
            partials.put(prodId, cost);

        });

        for (Double value : partials.values()) {
            total += value;
        }
        basket.setPartials(partials);
        basket.setTotal(total);
    }

    /**
     * facility method retriving the list of the Offers
     * @return
     */
    protected List<Offer> retrieveOffers() {
        RetrieveTask<Offer> task = new RetrieveTask<Offer>(Offer.class);
        RetrieveOperation<Offer> operation = new RetrieveOperation<>(Offer.class);
        operation.process(task);
        return task.getEntities();
    }

    /**
     * Facility method to retrieve the list of the Products
     * @return List<Product>
     */
    protected List<Product> retrieveProducts() {
        RetrieveTask<Product> task = new RetrieveTask<Product>(Product.class);
        RetrieveOperation<Product> operation = new RetrieveOperation<>(Product.class);
        operation.process(task);
        return task.getEntities();
    }

    /**
     * Facility to get a Map of products from a given List
     * @param products
     * @return DataMap<Product>
     */
    protected DataMap<Product> getProductsMap(List<Product> products) {
        DataMap<Product> productMap = new DataMap<>();
        products.forEach(product -> {
            productMap.put(product.getId(), product);
        });
        return productMap;
    }

    /**
     * Helper method to get the Offer for a given product id
     * @param offers
     * @param prodId
     * @return Offer
     */
    protected Offer getOffer(List<Offer> offers, String prodId) {
        if (offers != null && !offers.isEmpty() && prodId != null) {
            for (Offer offer : offers) {
                if (offer.getProductId().equals(prodId)) {
                    return offer;
                }
            }
        }
        return null;
    }
}
