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
@OpName("curation_reward")
public class CurationReward extends Operation {
    private String curator;
    private Asset reward;
    private String commentAuthor;
    private String commentPermlink;
}
