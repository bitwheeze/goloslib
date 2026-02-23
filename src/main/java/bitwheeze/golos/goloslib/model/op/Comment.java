package bitwheeze.golos.goloslib.model.op;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("comment")
public class Comment extends Operation {
    private String parentAuthor;
    private String parentPermlink;
    private String author;
    private String permlink;
    private String title;
    private String body;
    private String jsonMetadata;
}
