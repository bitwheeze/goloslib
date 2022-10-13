package bitwheeze.golos.goloslib.model.op;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("delete_comment")
@AllArgsConstructor
@NoArgsConstructor
public class DeleteComment extends Operation {
    private String author;
    private String permlink;
}
