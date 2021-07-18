package bitwheeze.golos.goloslib.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class WorkerRequestDeserializer  extends StdDeserializer<WorkerRequest> {
    public WorkerRequestDeserializer() {
        this(null);
    }
  
    public WorkerRequestDeserializer(Class<WorkerRequest> t) {
        super(t);
    }

    @Override
    public WorkerRequest deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        WorkerRequest ret = new WorkerRequest();
        JsonNode node = p.readValueAsTree();
        if(!node.isArray()) {
            throw new RuntimeException("WorkerRequest as an array expected");
        }
        if(node.size() != 2) {
            throw new RuntimeException("WorkerRequest as an array of 2 elements (String, long) expected");
        }
        
        ret.setId(node.get(0).asText());
        ret.setVolume(node.get(1).asLong());
        
        return ret;
    }

}
