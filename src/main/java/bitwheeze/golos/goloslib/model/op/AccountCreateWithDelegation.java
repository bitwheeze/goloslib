package bitwheeze.golos.goloslib.model.op;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.Authority;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("account_create_with_delegation")
public class AccountCreateWithDelegation extends Operation {
    private Asset fee;
    private Asset delegation;
    private String creator;
    private String newAccountName;
    private Authority owner;
    private Authority active;
    private Authority posting;
    private String memoKey;
    private String jsonMetadata;
    private AccountCreateWithDelegationExtension extensions;
}


