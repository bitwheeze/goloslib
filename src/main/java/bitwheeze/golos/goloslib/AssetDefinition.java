package bitwheeze.golos.goloslib;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class AssetDefinition {
    long id;
    String creator;
    String maxSupply;
    String supply;
    String canIssue;
    int precision;
    boolean allowFee;
    boolean allowOverrideTransfer;
    String jsonMetadata;
    LocalDateTime created;
    LocalDateTime modified;
    LocalDateTime marketed;
    String[] symbolsWhitelist;
    int feePercent;
}
