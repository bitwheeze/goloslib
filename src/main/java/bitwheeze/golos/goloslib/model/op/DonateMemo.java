package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DonateMemo {
    private String app;
    private int version;
    @JsonDeserialize(using = ToStringDeserializer.class)
    @JsonRawValue
    private String target;
    private String comment;
}
