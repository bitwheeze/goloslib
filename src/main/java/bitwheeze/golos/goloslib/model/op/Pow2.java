package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@OpName("pow2")
public class Pow2 extends Operation {
    @JsonDeserialize(using = ToStringDeserializer.class)
    private String work;
}

