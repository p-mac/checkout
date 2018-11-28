package org.test.itv.checkout.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created on 21/11/18.
 */
public class MapperFactory {

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .enable(JsonParser.Feature.ALLOW_MISSING_VALUES);
    }
}
