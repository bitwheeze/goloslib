package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.ToStringArrayDeserializer;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("comment_options")
public class CommentOptions extends Operation {
    private String author;
    private String permlink;
    private Asset maxAcceptedPayout;
    private short percentSteemDollars;
    private boolean allowVotes;
    private boolean allowCurationRewards;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
