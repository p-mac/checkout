package org.test.itv.checkout.serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.util.MapperFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 25/11/18.
 */
public class JSONSerializer implements Serializer<JsonNode> {

    private ObjectMapper mapper = MapperFactory.getMapper();

    public JSONSerializer() { }

    @Override
    public void serialize(@NotNull DataOutput2 dataOutput2, @NotNull JsonNode jsonNode) throws IOException {
        dataOutput2.write(jsonNode.toString().getBytes());
    }
    
    @Override
    public JsonNode deserialize(@NotNull DataInput2 dataInput2, int i) throws IOException {
        DataInput2.DataInputToStream stream = new DataInput2.DataInputToStream(dataInput2);
        return mapper.readTree(stream);
    }

    public <E extends Entity> E bind(JsonNode node, Class<E> entityClass) throws IOException {
        return bind(node.toString(), entityClass);
    }

    public <T> T bind(Object node, Class<T> entityClass) throws IOException {
        String src = mapper.writeValueAsString(node);
        return mapper.readValue(src, entityClass);
    }

    public JsonNode bindToNode(Object node) throws IOException {
        String src = mapper.writeValueAsString(node);
        return mapper.readTree(src);
    }

    public <E extends Entity> E bind(String node, Class<E> entityClass) throws IOException {
        return mapper.readValue(node, entityClass);
    }

    public <E extends Entity> List<E> bind(List<JsonNode> nodes, Class<E> entityClass) throws IOException {
        List<E> result = new ArrayList<E>();

        result = nodes.stream().map(jsonNode -> {
            try {
                return bind(jsonNode, entityClass);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
        return result;
    }
}
