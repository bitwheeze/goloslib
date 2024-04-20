package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringArrayDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("nft_buy")
@AllArgsConstructor
public class NftBuy extends Operation {
    private String buyer;
    private String name;
    private int tokenId;
    private int orderId;
    private Asset price;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
