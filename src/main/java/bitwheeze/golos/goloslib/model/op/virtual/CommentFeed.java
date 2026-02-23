package bitwheeze.golos.goloslib.model.op.virtual;

import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("comment_feed")
public class CommentFeed extends Operation {
    String follower;
    String author;
    String hashlink;
    String permlink;
    String parentAuthor;
    String parentHashlink;
    String parentPermlink;
}
