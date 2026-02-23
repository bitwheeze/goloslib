package bitwheeze.golos.goloslib.model.op;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("limit_order_cancel")
public class LimitOrderCancel extends Operation {
    private String owner;
    private long orderid;
}

