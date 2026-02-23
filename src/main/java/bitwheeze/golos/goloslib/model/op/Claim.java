package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringArrayDeserializer;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("claim")
public class Claim extends Operation {
    String from;    
    String to;
    Asset amount;
    boolean to_vesting;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    String[] extensions;
}
