package org.test.itv.checkout.service;

import org.test.itv.checkout.datasource.DataSourceAdapter;
import org.test.itv.checkout.dto.DeleteTask;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.types.OperationType;
import org.test.itv.checkout.util.JsonUtil;
import org.test.itv.checkout.util.KEYS;

import java.util.List;

/**
 * Created on 26/11/18.
 */
public class DeleteOperation<E extends Entity> extends BaseOperation<E, DeleteTask<E>> {

    public DeleteOperation() {
        super(OperationType.DELETE);
    }


    public DeleteOperation(Class<E> entityClass) {
        super(entityClass, OperationType.DELETE);
    }

    @Override
    public void process(DeleteTask<E> task) {
        try {
            DataSourceAdapter resource = dataProvider.getDataSource(KEYS.APP_NAME);
            List<String> ids = JsonUtil.bindToList(task.getParams().get(KEYS.ID), String.class);
            resource.delete(ids, task.getEntityClass());
        } catch (Exception e) {
            String message = "ERROR during the Delete Operation for the Task ".concat(task.getId());
            logger.error(message, e);
            task.putError(message);
        }
    }
}
