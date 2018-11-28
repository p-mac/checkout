package org.test.itv.checkout.dto;

import java.util.Date;

/**
 * Created on 24/11/18.
 */
public class Entity extends BaseDTO {

    private String id;
    private String name;
    private Date created = new Date();
    private long token;

    public Entity() { }

    public Entity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Entity(String id, String name, Date created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }
}
