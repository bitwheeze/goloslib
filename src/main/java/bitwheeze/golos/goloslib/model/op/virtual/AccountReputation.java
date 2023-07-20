package bitwheeze.golos.goloslib.model.op.virtual;

import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("account_reputation")
public class AccountReputation extends Operation {
    String voter;
    String author;
    String reputationBefore;
    String reputationAfter;
    int voteWeight;
}
