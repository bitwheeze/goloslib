package bitwheeze.golos.goloslib.model;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class ToStringArrayDeserializer extends StdDeserializer<String[]> {

    public ToStringArrayDeserializer() {
        this(null);
    }

    public ToStringArrayDeserializer(Class<WorkerRequest> t) {
        super(Object.class);
    }

    @Override
    public String[] deserialize(JsonParser p, DeserializationContext ctxt) {
        final var tree = p.readValueAsTree();
        if(tree.isArray()) {
            final var elCount = tree.size();
            final var returnArray  = new String[elCount];
            for(int i = 0; i < elCount; i++) {
                final var el = tree.get(i);
                returnArray[i] = el.toString();
            }

            return returnArray;
        }
        log.warn("Expected array, got: {}", tree.toString());
        return new String[0];
    }
}