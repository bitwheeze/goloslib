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
@OpName("claim")
public class Claim extends Operation {
    String from;    
    String to;
    Asset amount;
    boolean to_vesting;
    @JsonDeserialize(using = ToStringDeserializer.class) 
    String extensions;
}
