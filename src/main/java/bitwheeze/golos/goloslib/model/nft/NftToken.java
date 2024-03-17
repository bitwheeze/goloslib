package bitwheeze.golos.goloslib.model.nft;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class NftToken {
    String id;
    String creator;
    String name;
    String owner;
    int token_id;
    Asset issueCost;
    Asset lastBuyPrice;
    String jsonMetadata;
    LocalDateTime issued;
    LocalDateTime lastUpdate;
    boolean selling;
    BigDecimal priceReal;
}
