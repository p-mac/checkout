package org.test.itv.checkout.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.test.itv.checkout.util.MapperFactory;

import java.io.Serializable;

/**
 * BaseDTO class provides a base implementation for any of the DTO / Entities in the application
 * any common method might be placed in here to be available in all subclasses.
 */
public class BaseDTO implements Serializable {

    /**
     * toString method overridden to provide a JSON representation as result
     * @return String
     */
    @Override
    public String toString() {
        try {
            return MapperFactory.getMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
