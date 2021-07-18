package bitwheeze.golos.goloslib.model.op;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("limit_order_create")
public class LimitOrderCreate extends Operation {
    private String owner;
    private long orderid;
    private Asset amountToSell;
    private Asset minToReceive;
    private boolean fillOrKill;
    private LocalDateTime expiration;
}

