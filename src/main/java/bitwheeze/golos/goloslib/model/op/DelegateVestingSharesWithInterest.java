package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("delegate_vesting_shares_with_interest")
@AllArgsConstructor
@NoArgsConstructor
public class DelegateVestingSharesWithInterest extends Operation {
    String delegator;
    String delegatee;
    Asset vestingShares;
    int interestRate;
    @JsonDeserialize(using = ToStringDeserializer.class)
    private String extensions;
}
