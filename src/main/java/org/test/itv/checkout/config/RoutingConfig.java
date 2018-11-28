package org.test.itv.checkout.config;

import org.test.itv.checkout.dto.BaseDTO;
import org.test.itv.checkout.dto.DataMap;

/**
 * Created on 27/11/18.
 */
public class RoutingConfig extends BaseDTO {
    public DataMap<RouteConfig> routes = new DataMap<>();


    public DataMap<RouteConfig> getRoutes() {
        return routes;
    }

    public void setRoutes(DataMap<RouteConfig> routes) {
        this.routes = routes;
    }
}
