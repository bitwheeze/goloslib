package bitwheeze.golos.goloslib.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ToStringDeserializer extends StdDeserializer<String> {

    public ToStringDeserializer() {
        this(null);
    }
  
    public ToStringDeserializer(Class<WorkerRequest> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return p.readValueAsTree().toString();
    }

}
