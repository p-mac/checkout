package org.test.itv.checkout.dto;

import org.test.itv.checkout.types.OperationType;

import java.util.List;

/**
 * Created on 26/11/18.
 */
public class UpdateTask<E extends Entity> extends Transport<E> {

    public UpdateTask() {
        super(OperationType.UPDATE);
    }

    public UpdateTask(Class<E> entityClass) {
        super(OperationType.UPDATE, entityClass);
    }

    public UpdateTask(Class<E> entityClass, List<E> entities) {
        super(OperationType.UPDATE, entityClass, entities);
    }

    public UpdateTask(Class<E> entityClass, DynaMap params) {
        super(OperationType.UPDATE, entityClass, params);
    }

    public UpdateTask(Class<E> entityClass, List<E> entities, DynaMap params) {
        super(OperationType.UPDATE, entityClass, entities, params);
    }
}
