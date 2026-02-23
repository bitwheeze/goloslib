package bitwheeze.golos.goloslib.model.op.virtual;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("comment_benefactor_reward")
public class CommentBenefactorReward extends Operation {
    private String benefactor;
    private String author;
    private String permlink;
    private Asset reward;
}
