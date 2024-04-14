package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.*;

@Data
@ToString
@OpName("withdraw_vesting")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawVesting extends Operation {
    String account;
    Asset vestingShares;
}
