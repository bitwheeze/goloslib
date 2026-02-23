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
@OpName("author_reward")
public class AuthorReward extends Operation {
    private String author;
    private String permlink;
    private Asset sbdPayout;
    private Asset steemPayout;
    private Asset vestingPayout;
}
