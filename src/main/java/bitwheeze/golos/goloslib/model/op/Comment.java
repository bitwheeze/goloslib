package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
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
