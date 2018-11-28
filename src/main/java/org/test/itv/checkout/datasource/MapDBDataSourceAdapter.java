package org.test.itv.checkout.datasource;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.exception.CreateOperationException;
import org.test.itv.checkout.exception.DeleteOperationException;
import org.test.itv.checkout.exception.UpdateOperationException;
import org.test.itv.checkout.handler.EntityHandler;
import org.test.itv.checkout.serializer.EntitySerializer;
import org.test.itv.checkout.serializer.JSONSerializer;
import org.test.itv.checkout.util.KEYS;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created on 23/11/18.
 */
public class MapDBDataSourceAdapter implements DataSourceAdapter {

    private String name;
    private DB db;

    public MapDBDataSourceAdapter() {
        init();
    }

    public MapDBDataSourceAdapter(String name) {
        this.name = name;
        init();
    }

    public MapDBDataSourceAdapter(String name, DB db) {
        this.name = name;
        this.db = db;
    }

    /**
     * init method initializes the DB creating a DB instance in memory
     * in this case it is used an OffHeap memory DB instance.
     *
     */
    @Override
    public DataSourceAdapter init() {
        this.db = DBMaker
                .memoryDB()
                .executorEnable()
                .make();
        return this;
    }


    /**
     * retrieve method performs a retrieve by id on a db collection using the given id list
     * @param idList
     * @param entityClass
     * @param <T>
     * @return List<T>
     */
    @Override
    public <T extends Entity> List<T> retrieve(List<String> idList, Class<T> entityClass) {
        ConcurrentMap<String, T> collection = db.hashMap(entityClass.getSimpleName(), Serializer.STRING, EntitySerializer.getSerialiser(entityClass)).createOrOpen();
        List<T> results = collection.values().stream().filter((entry) -> {
            return idList.contains(entry.getId());
        }).collect(Collectors.toList());
        return results;
    }

    /**
     * retrieve method performs a retrieve of entities based on a given criteria map
     * @param params
     * @param entityClass
     * @param <T>
     * @return List<T>
     */
    @Override
    public <T extends Entity> List<T> retrieve(LinkedHashMap<String, Object> params, Class<T> entityClass) {

        ConcurrentMap<String, T> collection = db.hashMap(entityClass.getSimpleName(), Serializer.STRING, EntitySerializer.getSerialiser(entityClass)).createOrOpen();
        JSONSerializer jsr = new JSONSerializer();
        List<JsonNode> nodes = collection.values().stream().map(object -> {
            try {
                return jsr.bindToNode(object);
            } catch (IOException e) {
                return null;
            }
        }).collect(Collectors.toList());

        List<T> results = nodes.stream().filter(jsonNode -> {
            return matchCriteria(jsonNode, params);
        }).map(jsonNode -> {
            try {
                return jsr.bind(jsonNode.toString(), entityClass);
            } catch (IOException e) {
                return null;
            }
        }).collect(Collectors.toList());

        return results;
    }

    /**
     * mathCriteria method performs a match on a JsonNode, representing an entity, for the given criteria map
     * @param jsonNode
     * @param params
     * @return boolean
     */
    private boolean matchCriteria(JsonNode jsonNode, LinkedHashMap<String, Object> params) {
        boolean check = true;
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext() && check == true) {
            Map.Entry<String, Object> entry = iterator.next();
            String sepa = String.valueOf(JsonPointer.SEPARATOR);
            String path = KEYS.SLASH;
            path += entry.getKey().replaceAll(KEYS.DOT, sepa);
            JsonNode node = jsonNode.at(path);
            check = (node != null && node.toString() != null && node.toString().equals(String.valueOf(entry.getValue())));
        }
        return check;
    }

    /**
     * crete method performs a create operation on a DB collection for the given entity List,
     * it throws CreateOperationException if the entity list is null or empty
     * @param entities
     * @param entityClass
     * @param <T>
     * @return List<T>
     * @throws CreateOperationException
     */
    @Override
    public <T extends Entity> List<T> create(List<T> entities, Class<T> entityClass) throws CreateOperationException {
        if (entities == null || entities.isEmpty()) {
            throw new CreateOperationException("ERROR Invalid parameters While Creating Entities ".concat(entityClass.getSimpleName()));
        }
        List<T> result = new ArrayList<T>();
        ConcurrentMap<String, T> collection = db.hashMap(entityClass.getSimpleName(), Serializer.STRING, EntitySerializer.getSerialiser(entityClass)).createOrOpen();
        EntityHandler<T> handler = EntityHandler.getHandler(entityClass);
        entities.forEach(entity -> {
            handler.handleToken(entity);
            T item = collection.putIfAbsent(entity.getId(), entity);
            if (item == null) {
                item = entity;
            }
            result.add(item);
        });
        db.commit();
        return result;
    }

    /**
     * update method performs an update operation on a DB collection for the given entity List,
     * it throws UpdateOperationException if the entity list is null or empty
     * @param entities
     * @param entityClass
     * @param <T>
     * @return List<T>
     * @throws UpdateOperationException
     */
    @Override
    public <T extends Entity> List<T> update(List<T> entities, Class<T> entityClass) throws UpdateOperationException {
        if (entities == null || entities.isEmpty()) {
            throw new UpdateOperationException("ERROR Invalid parameters While Updating Entities ".concat(entityClass.getSimpleName()));
        }
        List<T> result = new ArrayList<T>();
        ConcurrentMap<String, T> collection = db.hashMap(entityClass.getSimpleName(), Serializer.STRING, EntitySerializer.getSerialiser(entityClass)).createOrOpen();
        EntityHandler<T> handler = EntityHandler.getHandler(entityClass);
        entities.forEach(entity -> {
            T item = collection.get(entity.getId());
            if (item != null && item.getToken() == entity.getToken()) {
                handler.handleToken(entity);
                item = collection.put(entity.getId(), entity);

            }
            result.add(entity);
        });
        db.commit();
        return result;

    }

    /**
     * delete method performs a delete operation on a DB collection for the given id List,
     * it throws DeleteOperationException if the id list is null or empty
     * @param idList
     * @param entityClass
     * @param <T>
     * @throws DeleteOperationException
     */
    @Override
    public <T extends Entity> void delete(List<String> idList, Class<T> entityClass) throws DeleteOperationException {
        if (idList == null || idList.isEmpty()) {
            throw new DeleteOperationException("ERROR Invalid parameters While Deleting Entities ".concat(entityClass.getSimpleName()));
        }
        List<T> result = new ArrayList<T>();
        ConcurrentMap<String, T> collection = db.hashMap(entityClass.getSimpleName(), Serializer.STRING, EntitySerializer.getSerialiser(entityClass)).createOrOpen();
        idList.forEach(collection::remove);
        db.commit();
    }


    /**
     * dispose method performs the close operations for the DB
     */
    @Override
    public void dispose() {
        this.db.close();
    }
}
