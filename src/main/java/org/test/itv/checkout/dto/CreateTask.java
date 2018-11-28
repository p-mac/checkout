package org.test.itv.checkout.dto;

import org.test.itv.checkout.types.OperationType;

import java.util.List;

/**
 * Created on 26/11/18.
 */
public class CreateTask<E extends Entity> extends Transport<E> {

    public CreateTask() {
        super(OperationType.CREATE);
    }

    public CreateTask(Class<E> entityClass) {
        super(OperationType.CREATE, entityClass);
    }

    public CreateTask(Class<E> entityClass, List<E> entities) {
        super(OperationType.CREATE, entityClass, entities);
    }

    public CreateTask(Class<E> entityClass, DynaMap params) {
        super(OperationType.CREATE, entityClass, params);
    }

    public CreateTask(Class<E> entityClass, List<E> entities, DynaMap params) {
        super(OperationType.CREATE, entityClass, entities, params);
    }
}
