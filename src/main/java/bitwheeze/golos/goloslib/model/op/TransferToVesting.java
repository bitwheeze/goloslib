package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("transfer_to_vesting")
@AllArgsConstructor
@NoArgsConstructor
public class TransferToVesting {
    String from;
    String to;
    Asset amount;
}
