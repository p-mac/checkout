package org.test.itv.checkout.datasource;

import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.exception.CreateOperationException;
import org.test.itv.checkout.exception.DeleteOperationException;
import org.test.itv.checkout.exception.UpdateOperationException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created on 27/11/18.
 */
public interface DataSourceAdapter {
    DataSourceAdapter init();

    <T extends Entity> List<T> retrieve(List<String> idList, Class<T> entityClass);

    <T extends Entity> List<T> retrieve(LinkedHashMap<String, Object> params, Class<T> entityClass);

    <T extends Entity> List<T> create(List<T> entities, Class<T> entityClass) throws CreateOperationException;

    <T extends Entity> List<T> update(List<T> entities, Class<T> entityClass) throws UpdateOperationException;

    <T extends Entity> void delete(List<String> idList, Class<T> entityClass) throws DeleteOperationException;

    void dispose();
}
