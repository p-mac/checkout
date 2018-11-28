package org.test.itv.checkout.handler;

import org.test.itv.checkout.dto.Entity;

import java.util.List;

/**
 * Created on 27/11/18.
 */
public class EntityHandler<E extends Entity> {

    private Class<E> entityClass;

    public EntityHandler(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * handleToken method sets the token to the current milliseconds
     * @param entities
     */
    public void handleToken(List<E> entities) {
        if (entities != null && !entities.isEmpty()) {
            long now = System.currentTimeMillis();
            entities.forEach(entity -> {
                entity.setToken(now);
            });
        }
    }

    /**
     * handleToken method sets the token to the current milliseconds
     * @param entity
     */
    public void handleToken(E entity) {
        if (entity != null) {
            long now = System.currentTimeMillis();
            entity.setToken(now);
        }
    }

    /**
     * facility method to create an EntityHandler instance
     * @param entityClass
     * @param <E>
     * @return EntityHandler<E>
     */
    public static <E extends Entity> EntityHandler<E> getHandler(Class<E> entityClass) {
        return new EntityHandler<E>(entityClass);
    }
}
