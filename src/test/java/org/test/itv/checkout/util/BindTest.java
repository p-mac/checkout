package org.test.itv.checkout.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.test.itv.checkout.dto.Offer;

import java.io.IOException;

/**
 * Created on 28/11/18.
 */
public class BindTest {

    private static String src = "{ \"id\" : \"1\", \"name\": \"Apples\", \"productId\" : \"A\", \"quantity\" : 2, \"offer\" : 2.50 }";


    public static void main(String... args) {
        ObjectMapper mapper = MapperFactory.getMapper();
        try {
            Offer routes = mapper.readValue(src, Offer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
