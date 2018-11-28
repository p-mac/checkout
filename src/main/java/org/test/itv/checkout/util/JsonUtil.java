package org.test.itv.checkout.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.test.itv.checkout.logging.LogManager;
import org.test.itv.checkout.logging.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonUtil class provides facilities to bind objects to a specific type using the Jackson ObjetMapper class
 */
public class JsonUtil {

    private static final Logger logger = LogManager.getLogger(JsonUtil.class);

    private JsonUtil() { }

    /**
     * bindTo performs a bind of an given object to another object instance based on the provided Class<T>
     * using JSON as the main translation point (no Cast)
     * @param obj
     * @param bindClass
     * @param <T>
     * @return T instance
     */
    public static <T> T bindTo(Object obj, Class<T> bindClass) {
        String source = KEYS.EMPTY;
        try {
            source = MapperFactory.getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error while binding an object", e);
        }
        return bindString(source, bindClass);

    }

    /**
     * bindTo performs a bind of an given object to a List of a specific type based on the provided Class<T>
     * using JSON as the main translation point (no Cast)
     * @param obj
     * @param bindClass
     * @param <T>
     * @return List<T>
     */
    public static <T> List<T> bindToList(Object obj, Class<T> bindClass) {
        List<T> objects = new ArrayList<T>();
        String source = KEYS.EMPTY;
        try {
            source = MapperFactory.getMapper().writeValueAsString(obj);
            objects = bindStringToList(source, bindClass);
        } catch (JsonProcessingException e) {
            logger.error("Error while binding an object", e);
        }
        return objects;

    }

    /**
     * bindString performs a bind from a given json string to an object instance based on the provided Class<T>
     * @param source
     * @param bindClass
     * @param <T>
     * @return
     */
    public static <T> T bindString(String source, Class<T> bindClass) {
        ObjectMapper mapper = MapperFactory.getMapper();
        try {
            return mapper.readValue(source, bindClass);
        } catch (IOException e) {
            logger.error("Error while binding a json string", e);
        }
        return null;
    }

    /**
     * bindString performs a bind from a given json string to a List of a specific type based on the provided Class<T>
     * @param source
     * @param bindClass
     * @param <T>
     * @return List<T>
     */
    public static <T> List<T> bindStringToList(String source, Class<T> bindClass) {
        List<T> objects = new ArrayList<T>();
        List list = bindString(source, List.class);
        list.forEach(item -> {
            objects.add(bindTo(item, bindClass));
        });
        return objects;

    }


    /**
     * toJsonString is a facility to translate any given object into a proper json string representation
     * @param obj
     * @return String
     */
    public static String toJsonString(Object obj) {
        String value = KEYS.EMPTY;
        try {
            value = MapperFactory.getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error while writing an object to a json string", e);
        }
        return value;
    }
}
