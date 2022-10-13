package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("convert")
@AllArgsConstructor
@NoArgsConstructor
public class Convert extends Operation {
    String owner;
    long requestid;
    Asset amount;
}
