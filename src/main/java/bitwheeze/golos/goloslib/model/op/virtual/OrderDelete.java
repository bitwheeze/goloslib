package bitwheeze.golos.goloslib.model.op.virtual;

import bitwheeze.golos.goloslib.model.Price;
import bitwheeze.golos.goloslib.model.op.OpName;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("comment_delete")
public class OrderDelete {
    long orderId;
    String seller;
    Price sellPrice;
}
