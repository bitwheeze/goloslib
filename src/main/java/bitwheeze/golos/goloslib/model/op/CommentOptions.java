package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("comment_options")
public class CommentOptions extends Operation {
    private String author;
    private String permlink;
    private Asset maxAcceptedPayout;
    private short percentSteemDollars;
    private boolean allowVotes;
    private boolean allowCurationRewards;
    @JsonDeserialize(using = ToStringDeserializer.class)
    private String extensions;
}
