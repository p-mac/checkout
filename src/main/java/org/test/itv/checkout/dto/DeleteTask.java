package org.test.itv.checkout.dto;

import org.test.itv.checkout.types.OperationType;

import java.util.List;

/**
 * Created on 26/11/18.
 */
public class DeleteTask<E extends Entity> extends Transport<E> {

    public DeleteTask() {
        super(OperationType.DELETE);
    }

    public DeleteTask(Class<E> entityClass) {
        super(OperationType.DELETE, entityClass);
    }

    public DeleteTask(Class<E> entityClass, List<E> entities) {
        super(OperationType.DELETE, entityClass, entities);
    }

    public DeleteTask(Class<E> entityClass, DynaMap params) {
        super(OperationType.DELETE, entityClass, params);
    }
}
