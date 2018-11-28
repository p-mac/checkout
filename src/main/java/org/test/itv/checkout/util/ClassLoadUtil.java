package org.test.itv.checkout.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created on 26/11/18.
 */
public class ClassLoadUtil {

    private static ObjectMapper mapper = MapperFactory.getMapper();

    private ClassLoadUtil() { }

    /**
     * getObject method is a facility to obtain an instance from a Class based on a given fully qualified class name
     * @param className
     * @param <T>
     * @return T
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T getObject(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<T> objectClass = getObjectClass(className);
        return objectClass.newInstance();
    }

    /**
     * getObjectClass is a facility to obtain a class of a specific type <T> based on a given fully qualified class name
     * @param className
     * @param <T>
     * @return Class<T>
     * @throws ClassNotFoundException
     */
    public static <T> Class<T> getObjectClass(String className) throws ClassNotFoundException {
        Class objetClass = ClassLoadUtil.class.getClassLoader().loadClass(className);
        return (Class<T>) objetClass;
    }
}
