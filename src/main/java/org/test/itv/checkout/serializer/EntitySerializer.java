package org.test.itv.checkout.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.util.MapperFactory;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created on 24/11/18.
 */
public class EntitySerializer<E extends Entity> implements Serializer<E> {
    private ObjectMapper mapper = MapperFactory.getMapper();
    private Class<E> entityClass;

    public EntitySerializer(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * it serializes an Entity to the DataOutput2 calling the write method which uses Jackson mapper serialization
     * @param dataOutput2
     * @param entity
     * @throws IOException
     */
    @Override
    public void serialize(@NotNull DataOutput2 dataOutput2, @NotNull E entity) throws IOException {
        write(dataOutput2, entity);
    }

    /**
     * it writes a given entity Json serialization to a given OutputStream
     * @param outputStream
     * @param entity
     * @throws IOException
     */
    private void write(OutputStream outputStream, E entity) throws IOException {
        mapper.writeValue(outputStream, entity);
    }

    /**
     * it deserializes a given DataInput2 into a sperific Entity instance,
     * based on the entityClass provided in the Serializer instance
     * @param dataInput2
     * @param i
     * @return
     * @throws IOException
     */
    @Override
    public E deserialize(@NotNull DataInput2 dataInput2, int i) throws IOException {
        return mapper.readValue(dataInput2, this.entityClass);
    }

    /**
     * getSerializer static method is a facility (Factory method) to create a Serializer instance passing the required Entity Class
     * @param entityClass
     * @param <E>
     * @return DeleteOperationException<E>
     */
    public static <E extends Entity> EntitySerializer<E> getSerialiser(Class<E> entityClass) {
        return new EntitySerializer<>(entityClass);
    }
}
