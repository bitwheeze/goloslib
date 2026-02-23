package bitwheeze.golos.goloslib.model.op;

import java.io.IOException;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class OperationPackSerializer extends StdSerializer<OperationPack>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OperationPackSerializer() {
        this(null);
    }
    
    protected OperationPackSerializer(Class<OperationPack> t) {
        super(t);
    }

    @Override
    public void serialize(OperationPack value, JsonGenerator gen, SerializationContext provider) {
        
        gen.writeStartArray();
        gen.writeString(value.getOpName());
        gen.writePOJO(value.getOp());
        gen.writeEndArray();
    }

}
