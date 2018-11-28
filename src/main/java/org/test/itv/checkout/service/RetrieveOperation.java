package org.test.itv.checkout.service;

import org.test.itv.checkout.datasource.DataSourceAdapter;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.dto.RetrieveTask;
import org.test.itv.checkout.types.OperationType;
import org.test.itv.checkout.util.KEYS;

import java.util.List;

/**
 * Created on 26/11/18.
 */
public class RetrieveOperation<E extends Entity> extends BaseOperation<E, RetrieveTask<E>> {

    public RetrieveOperation() {
        super(OperationType.RETRIEVE);
    }


    public RetrieveOperation(Class<E> entityClass) {
        super(entityClass, OperationType.RETRIEVE);
    }

    @Override
    public void process(RetrieveTask<E> task) {
        try {
            DataSourceAdapter resource = dataProvider.getDataSource(KEYS.APP_NAME);
            List<E> result = resource.retrieve(task.getParams(), task.getEntityClass());
            task.setEntities(result);
        } catch (Exception e) {
            String message = "ERROR during the Retrieve Operation for the Task ".concat(task.getId());
            logger.error(message, e);
            task.putError(message);
        }
    }
}
