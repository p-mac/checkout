package org.test.itv.checkout.dto;

import org.test.itv.checkout.types.OperationType;

import java.util.List;

/**
 * Created on 26/11/18.
 */
public class RetrieveTask<E extends Entity> extends Transport<E> {

    public RetrieveTask() {
        super(OperationType.RETRIEVE);
    }

    public RetrieveTask(Class<E> entityClass) {
        super(OperationType.RETRIEVE, entityClass);
    }

    public RetrieveTask(Class<E> entityClass, List<E> entities) {
        super(OperationType.RETRIEVE, entityClass, entities);
    }

    public RetrieveTask(Class<E> entityClass, DynaMap params) {
        super(OperationType.RETRIEVE, entityClass, params);
    }

}
