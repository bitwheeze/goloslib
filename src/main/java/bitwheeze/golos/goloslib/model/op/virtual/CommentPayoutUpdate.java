package bitwheeze.golos.goloslib.model.op.virtual;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("comment_payout_update")
public class CommentPayoutUpdate extends Operation {
    private String author;
    private String permlink;
}
