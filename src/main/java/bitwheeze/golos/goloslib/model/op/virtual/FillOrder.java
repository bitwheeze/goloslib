package bitwheeze.golos.goloslib.model.op.virtual;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.op.OpName;
import bitwheeze.golos.goloslib.model.op.Operation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("fill_order")
public class FillOrder extends Operation {
    private String currentOwner;
    private long currentOrderid;
    private Asset currentPays;
    private Asset currentTradeFee;
    private String currentTradeFeeReceiver;

    private String openOwner;
    private long openOrderid;
    private Asset openPays;
    private Asset openTradeFee;
    private String openTradeFeeReceiver;
}
