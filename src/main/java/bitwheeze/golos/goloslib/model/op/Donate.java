package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("donate")
public class Donate extends Operation {
    private String from;
    private String to;
    private Asset amount;
    private DonateMemo memo;
    @JsonDeserialize(using = ToStringDeserializer.class)    
    private String extensions;
}
