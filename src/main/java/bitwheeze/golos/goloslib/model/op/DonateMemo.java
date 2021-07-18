package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DonateMemo {
    private String app;
    private int version;
    @JsonDeserialize(using = ToStringDeserializer.class)    
    private String target;
    private String memo;
}
