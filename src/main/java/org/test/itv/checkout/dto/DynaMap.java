package org.test.itv.checkout.dto;


import java.util.Map;

/**
 * Created on 27/11/18.
 */
public class DynaMap extends DataMap<Object> {

    public DynaMap() { }

    public DynaMap(Map<? extends String, ?> m) {
        super(m);
    }


    public DynaMap add(String key, Object value) {
        put(key, value);
        return this;
    }

    public static DynaMap set(String key, Object value) {
        return new DynaMap().add(key, value);
    }
}
