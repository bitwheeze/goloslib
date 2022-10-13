package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("account_create_with_invite")
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateWithInvite extends Operation {
    String inviteSecret;
    String creator;
    String newAccountName;
    private Authority owner;
    private Authority active;
    private Authority posting;
    private String memoKey;
    private String jsonMetadata;
}
