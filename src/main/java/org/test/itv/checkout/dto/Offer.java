package org.test.itv.checkout.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.test.itv.checkout.util.KEYS;

import java.beans.Transient;
import java.util.Date;

/**
 * Created on 22/11/18.
 */
public class Offer extends Entity {

    private String productId;
    private Integer quantity = 2;
    private Double offer;
    private Date startDate = new Date();
    private Date expires = new Date((startDate.getTime()+ KEYS.day));

    public Offer() { }

    public Offer(String id, String name) {
        super(id, name);
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    /**
     * isBetween method checks if the Offer start and end date are matching a given Date value (BETWEEN)
     * @param date
     * @return Boolean
     */
    @JsonIgnore
    @Transient
    public Boolean isBetween(Date date) {
        long time = date.getTime();
        return isBetween(time);
    }

    /**
     * isBetween method checks if the Offer start and end date are matching a given Long date value (BETWEEN)
     * @param date
     * @return Boolean
     */
    @JsonIgnore
    @Transient
    public Boolean isBetween(Long date) {
        return (date >= startDate.getTime() && date <= expires.getTime());
    }

    /**
     * isValid method checks if the Offer is valid based on the current system milliseconds and the Offer start and end dates (BETWEEN)
     * @return Boolean
     */
    @JsonIgnore
    @Transient
    public Boolean isValid() {
        long time = System.currentTimeMillis();
        return isBetween(time);
    }
}
