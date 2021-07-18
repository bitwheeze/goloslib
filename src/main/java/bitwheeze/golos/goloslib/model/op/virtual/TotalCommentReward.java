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
@OpName("total_comment_reward")
public class TotalCommentReward extends Operation {
    private String author;
    private String permlink;
    private Asset authorReward;
    private Asset benefactorReward;
    private Asset curatorReward;
    private long netRshares;
}
