package bitwheeze.golos.goloslib.model.op;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("custom_json")
public class CustomJson extends Operation {
    private String [] requiredAuths;
    private String [] requiredPostingAuths;
    private String id;
    private String json;
}
