package bitwheeze.golos.goloslib.model.op;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

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
    public void serialize(OperationPack value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        
        gen.writeStartArray();
        gen.writeString(value.getOpName());
        gen.writeObject(value.getOp());
        gen.writeEndArray();
    }

}
