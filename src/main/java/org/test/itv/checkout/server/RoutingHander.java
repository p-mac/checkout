package org.test.itv.checkout.server;

import org.test.itv.checkout.config.RouteConfig;
import org.test.itv.checkout.dto.DynaMap;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.logging.LogManager;
import org.test.itv.checkout.logging.Logger;
import org.test.itv.checkout.util.ClassLoadUtil;
import org.test.itv.checkout.util.JsonUtil;
import org.test.itv.checkout.util.KEYS;
import spark.Request;
import spark.Route;

import java.util.List;
import java.util.Map;

/**
 * Created on 27/11/18.
 */
public abstract class RoutingHander<E extends Entity> implements Route {

    protected Logger logger;
    protected RouteConfig config;
    protected Class<E> entityClass;

    public RoutingHander() {
        this.logger = LogManager.getLogger(getClass());
    }

    public RoutingHander(RouteConfig config) {
        this.config = config;
        this.logger = LogManager.getLogger(getClass());
        try {
            this.entityClass = ClassLoadUtil.getObjectClass(config.getEntityClassName());
        } catch (ClassNotFoundException e) {
            logger.error("ERROR while loading the EntityClass in RoutingHandler", e);
        }
    }

    public RouteConfig getConfig() {
        return config;
    }

    public void setConfig(RouteConfig config) {
        this.config = config;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public DynaMap getParams(Request request) {
        DynaMap map = normalize(request.params());
        map.putAll(request.queryMap().toMap());
        return map;
    }

    protected DynaMap normalize(Map<String, String> pathParams) {
        DynaMap params = new DynaMap();
        pathParams.entrySet().forEach(entry -> {
            String key = entry.getKey();
            String name = key.replace(KEYS.COLON, KEYS.BLANK).replaceAll(KEYS.RX_DOT, KEYS.UNDERSCORE);
            params.put(name, entry.getValue());
        });
        return params;
    }

    public E getEntity(Request request) throws Exception {
        return JsonUtil.bindString(request.body(), entityClass);
    }

    public List<E> getEntityList(Request request) throws Exception {
        return JsonUtil.bindToList(request.body(), entityClass);
    }
}
