package org.test.itv.checkout.dto;

import org.test.itv.checkout.util.JsonUtil;
import org.test.itv.checkout.util.KEYS;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created on 27/11/18.
 */
public class DataMap<T> extends LinkedHashMap<String, T> {

    public DataMap() { }

    public DataMap(Map<? extends String, ? extends T> m) {
        super(m);
    }

    public String getString(String name) {
        Object value = get(name);
        return (value != null) ? String.valueOf(value) : null;
    }

    public Number getNumber(String name) {
        Object object = get(name);
        if (object != null) {
            try {
                String value = String.valueOf(object);
                return  (value.contains(KEYS.DOT)) ? Double.parseDouble(value) : Integer.parseInt(value);
            } catch (Exception e) { }
        }
        return -1;
    }

    public Boolean getBoolean(String name) {
        Object object = get(name);
        if (object != null) {
            String value = String.valueOf(object);
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public Date getDate(String name) {
        Object object = get(name);
        if (object != null) {
            return JsonUtil.bindTo(object, Date.class);
        }
        return null;
    }

    public <T> T getObject(String name, Class<T> objetClass) {
        Object object = get(name);
        if (object != null) {
            return JsonUtil.bindTo(object, objetClass);
        }
        return null;
    }

    public DataMap<T> add(String key, T value) {
        put(key, value);
        return this;
    }

    public static <T> DataMap<T> set(String key, T value) {
        return new DataMap<T>().add(key, value);
    }
}
