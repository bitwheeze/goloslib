package bitwheeze.golos.goloslib.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@ToString
public class OpenOrder {
    long id;
    LocalDateTime created;
    LocalDateTime expiration;
    String seller;
    long orderid;
    BigInteger forSale;
    Price sellPrice;
    BigDecimal realPrice;
    boolean rewarded;
    Asset asset1;
    Asset asset2;
}
