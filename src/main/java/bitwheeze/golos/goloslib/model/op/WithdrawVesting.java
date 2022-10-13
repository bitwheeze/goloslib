package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("withdraw_vesting")
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawVesting extends Operation {
    String account;
    Asset vestingShares;
}
