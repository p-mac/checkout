package org.test.itv.checkout.service;

import org.test.itv.checkout.datasource.DataSourceAdapter;
import org.test.itv.checkout.dto.CreateTask;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.types.OperationType;
import org.test.itv.checkout.util.KEYS;

import java.util.List;

/**
 * Created on 26/11/18.
 */
public class CreateOperation<E extends Entity> extends BaseOperation<E, CreateTask<E>> {

    public CreateOperation() {
        super(OperationType.CREATE);
    }


    public CreateOperation(Class<E> entityClass) {
        super(entityClass, OperationType.CREATE);
    }

    @Override
    public void process(CreateTask<E> task) {
        try {
            DataSourceAdapter resource = dataProvider.getDataSource(KEYS.APP_NAME);
            List<E> result = resource.create(task.getEntities(), task.getEntityClass());
            task.setEntities(result);
        } catch (Exception e) {
            String message = "ERROR during the Create Operation for the Task ".concat(task.getId());
            logger.error(message, e);
            task.putError(message);
        }
    }
}
