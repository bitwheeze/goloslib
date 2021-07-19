package bitwheeze.golos.goloslib.model;

import bitwheeze.golos.goloslib.model.op.AccountAuthority;
import bitwheeze.golos.goloslib.model.op.KeyAuthority;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Authority {
    private int weightThreshold;
    private AccountAuthority[] accountAuths;
    private KeyAuthority[] keyAuths;
}
