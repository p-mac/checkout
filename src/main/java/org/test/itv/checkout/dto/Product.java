package org.test.itv.checkout.dto;

import java.util.Date;

/**
 * Created on 22/11/18.
 */
public class Product extends Entity {

    private String description;
    private Double price = 0.1D;

    public Product() { }

    public Product(String id, String name) {
        super(id, name);
    }

    public Product(String id, String name, Date created) {
        super(id, name, created);
    }

    public Product(String id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public Product(String id, String name, Double price) {
        super(id, name);
        this.price = price;
    }

    public Product(String id, String name, String description, Double price) {
        super(id, name);
        this.description = description;
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
