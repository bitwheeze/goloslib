package bitwheeze.golos.goloslib.model;

import java.io.IOException;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

public class WorkerRequestDeserializer  extends StdDeserializer<WorkerRequest> {
    public WorkerRequestDeserializer() {
        this(WorkerRequest.class);
    }
  
    public WorkerRequestDeserializer(Class<WorkerRequest> t) {
        super(t);
    }

    @Override
    public WorkerRequest deserialize(JsonParser p, DeserializationContext ctxt)
            {
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
