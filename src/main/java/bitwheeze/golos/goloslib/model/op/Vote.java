package bitwheeze.golos.goloslib.model.op;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@OpName("vote")
public class Vote extends Operation {
    String voter;
    String author;
    String permlink;
    int weight;
}
