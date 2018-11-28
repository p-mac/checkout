package org.test.itv.checkout.service;

import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.dto.Task;
import org.test.itv.checkout.types.OperationType;

/**
 * Created on 26/11/18.
 */
public interface Operation<E extends Entity, T extends Task<E>> {

    Class<E> getEntityClass();

    OperationType getType();

    /**
     * intialises the Operation
     */
    void init();

    /**
     * process method represets the entry point for accessing the Operation
     * it performs the operation specific task and fill the Transport with the result
     * @param task
     */
    void process(T task);

    /**
     * executes the destroy for the Operation
     */
    void dispose();
}
