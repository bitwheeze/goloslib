package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("transfer")
@AllArgsConstructor
@NoArgsConstructor
public class Transfer extends Operation {
    String from;
    String to;
    Asset amount;
    String memo;    
}
