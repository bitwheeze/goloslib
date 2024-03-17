package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("nft_sell")
@AllArgsConstructor
public class NftSell extends Operation {
    private String seller;
    private int tokenId;
    private String buyer;
    private int orderId;
    private Asset price;
    private String[] extensions;
}
