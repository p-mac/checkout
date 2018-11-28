package org.test.itv.checkout.service;

import org.test.itv.checkout.config.ConfigManager;
import org.test.itv.checkout.dto.*;
import org.test.itv.checkout.util.ClassLoadUtil;
import org.test.itv.checkout.util.JsonUtil;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper Operation to automatically load some data into the In Memory DB for a given entity
 */
public class LoadServiceOperation<E extends Entity> extends RetrieveOperation<E> {

    public LoadServiceOperation() {
        super();
    }

    public LoadServiceOperation(Class<E> entityClass) {
        super(entityClass);
    }

    /**
     * the process method in this case attempts the data loading for the given entity
     * and returns back the created entities
     * @param task
     */
    @Override
    public void process(RetrieveTask<E> task) {
        String entityName = task.getParams().getString("entity");
        try {
            DataMap<String> entities = ConfigManager.getInsntance().getConfig("entities", DataMap.class);
            if (entities != null && !entities.isEmpty()) {
                String className = entities.get(entityName);
                Class<E> entityClass = ClassLoadUtil.getObjectClass(className);
                task.setEntityClassName(className);
                super.setEntityClass(entityClass);
                List<E> entitiesList = getEntities(entityName.toLowerCase(), entityClass);
                CreateOperation<E> operation = new CreateOperation<E>(entityClass);
                CreateTask<E> createTask = new CreateTask<E>(entityClass, entitiesList);
                operation.process(createTask);
                task.setEntities(createTask.getEntities());
            }
        } catch (Exception e) {
            logger.error("ERROR while loading the entities : ".concat(entityName), e);
        }
    }

    private <E> List<E> getEntities(String entityName, Class<E> entityClass) throws InvalidObjectException {
        List<E> list = ConfigManager.getInsntance().getConfig(entityName.toLowerCase(), ArrayList.class);
        return JsonUtil.bindToList(list, entityClass);
    }
}
