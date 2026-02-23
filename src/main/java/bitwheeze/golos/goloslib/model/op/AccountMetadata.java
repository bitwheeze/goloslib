package bitwheeze.golos.goloslib.model.op;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("account_metadata")
public class AccountMetadata extends Operation {
    private String account;
    private String jsonMetadata;
}


