package bitwheeze.golos.goloslib.model.op.virtual;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.Price;
import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("order_create")
public class OrderCreate extends Operation {
    long orderid;
    LocalDateTime created;
    LocalDateTime expiration;
    String seller;
    Asset forSale;
    Price sellPrice;
}
