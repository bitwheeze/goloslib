package bitwheeze.golos.goloslib.model.op;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Authority {
    private int weightThreshold;
    private AccountAuthority[] accountAuths;
    private KeyAuthority[] keyAuths;
}
