package org.test.itv.checkout.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.test.itv.checkout.types.OperationType;
import org.test.itv.checkout.util.ClassLoadUtil;

import java.beans.Transient;
import java.util.*;

/**
 * Created on 26/11/18.
 */
public class Transport<T extends Entity> extends BaseDTO implements Task<T> {

    private String id = UUID.randomUUID().toString();
    private OperationType operationType = OperationType.RETRIEVE;
    private String entityClassName;
    private List<T> entities = new ArrayList<T>();
    private DynaMap params = new DynaMap();
    private TaskError error;

    public Transport() { }

    public Transport(OperationType operationType) {
        this.operationType = operationType;
    }

    public Transport(OperationType operationType, Class<T> entityClass) {
        this.operationType = operationType;
        this.entityClassName = entityClass.getCanonicalName();
    }

    public Transport(OperationType operationType, Class<T> entityClass, List<T> entities) {
        this.operationType = operationType;
        this.entities = entities;
        this.entityClassName = entityClass.getCanonicalName();
    }

    public Transport(OperationType operationType, Class<T> entityClass, DynaMap params) {
        this.operationType = operationType;
        this.params = params;
        this.entityClassName = entityClass.getCanonicalName();
    }

    public Transport(OperationType operationType, Class<T> entityClass, List<T> entities, DynaMap params) {
        this.operationType = operationType;
        this.entityClassName = entityClass.getCanonicalName();
        this.entities = entities;
        this.params = params;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    @Override
    public List<T> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    @Override
    public DynaMap getParams() {
        return params;
    }

    public void setParams(DynaMap params) {
        this.params = params;
    }

    @Override
    public TaskError getError() {
        return error;
    }

    @Override
    public void setError(TaskError error) {
        this.error = error;
    }

    @Override
    @JsonIgnore
    @Transient
    public Class<T> getEntityClass() throws ClassNotFoundException {
        return ClassLoadUtil.getObjectClass(this.entityClassName);
    }

    @JsonIgnore
    @Transient
    public Task<T> put(String name, Object value) {
        params.put(name, value);
        return this;
    }

    @JsonIgnore
    @Transient
    public Task<T> put(T entity) {
        int index = entities.indexOf(entity);
        if (index != -1) {
            entities.set(index, entity);
        } else {
            entities.add(entity);
        }
        return this;
    }


    @Override
    @JsonIgnore
    @Transient
    public void putError(String message) {
        this.error = new TaskError(entityClassName, message);
    }

    @Override
    @JsonIgnore
    @Transient
    public void putError(String code, String message) {
        this.error = new TaskError(entityClassName, code, message);
    }


    public static <T extends Entity> Task<T> set(OperationType type, Class<T> entityClass, T... entities) {
        return new Transport<T>(type, entityClass, new ArrayList<T>(Arrays.asList(entities)));
    }

}
