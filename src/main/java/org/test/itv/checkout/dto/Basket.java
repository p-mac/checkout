package org.test.itv.checkout.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;

/**
 * Created on 22/11/18.
 */
public class Basket extends Entity {

    private DataMap<Integer> items = new DataMap<>();
    private DataMap<Double> partials = new DataMap<>();

    private Double total = 0D;

    public Basket() { }

    public Basket(String id, String name) {
        super(id, name);
    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public DataMap<Integer> getItems() {
        return items;
    }

    public void setItems(DataMap<Integer> items) {
        this.items = items;
    }

    public DataMap<Double> getPartials() {
        return partials;
    }

    public void setPartials(DataMap<Double> partials) {
        this.partials = partials;
    }

    @JsonIgnore
    @Transient
    public void addItem(String item) {
        Integer size = items.get(item);
        if (size == null) {
            size = 0;
        }
        items.put(item, size++);
    }

    @JsonIgnore
    @Transient
    public void addItem(String item, Integer num) {
        Integer size = items.get(item);
        if (size == null) {
            size = 0;
        }
        items.put(item, size+num);
    }

    @JsonIgnore
    @Transient
    public void removeItem(String item) {
        Integer size = items.get(item);
        size = (size == null) ? 0 : size-1;
        items.put(item, size);
    }

    @JsonIgnore
    @Transient
    public void removeItem(String item, Integer num) {
        Integer size = items.get(item);
        size = (size == null) ? 0 : size-num;
        items.put(item, size);
    }

    @JsonIgnore
    @Transient
    public void removeFullItem(String item) {
        items.remove(item);
    }

    @JsonIgnore
    @Transient
    public Integer getItem(String item) {
        return items.get(item);
    }


}
