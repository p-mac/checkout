package org.test.itv.checkout.config;

import org.test.itv.checkout.dto.BaseDTO;
import org.test.itv.checkout.types.HttpMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * simple DTO for the
 */
public class RouteConfig extends BaseDTO {

    private String uri;
    private String name;
    private HttpMethods method = HttpMethods.GET;
    private List<String> params = new ArrayList<>();
    private String entityClassName;
    private String taskClassName;
    private String serviceClassName;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpMethods getMethod() {
        return method;
    }

    public void setMethod(HttpMethods method) {
        this.method = method;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getTaskClassName() {
        return taskClassName;
    }

    public void setTaskClassName(String taskClassName) {
        this.taskClassName = taskClassName;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
