package bitwheeze.golos.goloslib.model.op.virtual;

import bitwheeze.golos.goloslib.model.Price;
import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("order_delete")
public class OrderDelete extends Operation {
    long orderId;
    String seller;
    Price sellPrice;
}
