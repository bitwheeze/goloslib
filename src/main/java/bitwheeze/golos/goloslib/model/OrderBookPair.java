package bitwheeze.golos.goloslib.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class OrderBookPair {
    BigDecimal price;
    BigDecimal asset1;
    BigDecimal asset2;
}
