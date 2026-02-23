package bitwheeze.golos.goloslib.model.op;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Authority;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("account_update")
public class AccountUpdate extends Operation {
    private String account;
    private Authority owner;
    private Authority active;
    private Authority posting;
    private String memoKey;
    private String jsonMetadata;
}


