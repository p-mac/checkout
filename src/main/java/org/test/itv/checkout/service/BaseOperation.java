package org.test.itv.checkout.service;

import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.dto.Transport;
import org.test.itv.checkout.logging.LogManager;
import org.test.itv.checkout.logging.Logger;
import org.test.itv.checkout.types.OperationType;

/**
 * Created on 26/11/18.
 */
public abstract class BaseOperation<E extends Entity, T extends Transport<E>> implements Operation<E, T> {

    protected static Logger logger;
    protected Class<E> entityClass;
    protected OperationType type;
    protected DataSourceProvider dataProvider = DataSourceProvider.getInstance();

    public BaseOperation() {
        logger = LogManager.getLogger(getClass());
    }

    public BaseOperation(OperationType type) {
        this.type = type;
        logger = LogManager.getLogger(getClass());
    }

    public BaseOperation(Class<E> entityClass, OperationType type) {
        this.entityClass = entityClass;
        this.type = type;
        logger = LogManager.getLogger(getClass());
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    @Override
    public void init() {

    }

    @Override
    public abstract void process(T task);

    @Override
    public void dispose() {

    }
}
