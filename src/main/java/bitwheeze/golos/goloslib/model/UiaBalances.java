package bitwheeze.golos.goloslib.model;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UiaBalances {
    long id;
    String account;
    Asset balance;
    Asset tipBalance;
    Asset marketBalance;
}
