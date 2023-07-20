package bitwheeze.golos.goloslib.model.exception;

import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NodeError {
    long code;
    String message;
    @JsonDeserialize(using = ToStringDeserializer.class)
    String data;
}
