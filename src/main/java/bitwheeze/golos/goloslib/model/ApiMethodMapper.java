package bitwheeze.golos.goloslib.model;

import java.io.IOException;

import bitwheeze.golos.goloslib.model.api.ApiMethod;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class ApiMethodMapper  extends StdSerializer<ApiMethod> {
    public ApiMethodMapper() {
        this(ApiMethod.class);
    }
  
    public ApiMethodMapper(Class<ApiMethod> t) {
        super(t);
    }

    @Override
    public void serialize(ApiMethod value, JsonGenerator gen, SerializationContext provider) {
        gen.writeStartArray();
        gen.writeString("");
        
    }
}
