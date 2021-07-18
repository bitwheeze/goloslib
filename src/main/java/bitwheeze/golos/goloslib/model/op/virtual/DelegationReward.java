package bitwheeze.golos.goloslib.model.op.virtual;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("delegation_reward")
public class DelegationReward extends Operation {
    private String delegator;
    private String delegatee;
    private String payoutStrategy;
    private Asset vestingShares;
}
