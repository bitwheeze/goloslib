package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@JsonDeserialize(using = OperationPackDeserializer.class)
@JsonSerialize(using = OperationPackSerializer.class) 
public class OperationPack {
    private String opName;
    private Operation op;
    
    public OperationPack(Operation op) {
        this.op = op;
        
        var opName = op.getClass().getAnnotation(OpName.class);
        if(null == opName || opName.value() == null || opName.value().isBlank()) {
            throw new RuntimeException("No opName annotation found for Operation-Class " + op.getClass().getName());
        }
        
        this.opName = opName.value();
    }
}
