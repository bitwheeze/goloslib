package bitwheeze.golos.goloslib.model;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ToStringDeserializer extends StdDeserializer<String> {

    public ToStringDeserializer() {
        this(null);
    }
  
    public ToStringDeserializer(Class<WorkerRequest> t) {
        super(Object.class);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) {
        return p.readValueAsTree().toString();
    }
}
