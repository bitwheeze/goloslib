package bitwheeze.golos.goloslib.model;

import java.io.IOException;

import bitwheeze.golos.goloslib.model.api.ApiMethod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ApiMethodMapper  extends StdSerializer<ApiMethod> {
    public ApiMethodMapper() {
        this(null);
    }
  
    public ApiMethodMapper(Class<ApiMethod> t) {
        super(t);
    }

    @Override
    public void serialize(ApiMethod value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        gen.writeString("");
        
    }
}
