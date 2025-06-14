package bitwheeze.golos.goloslib.model.exchange;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.GolosConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.util.Set;

@Value
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeQuery {
        Asset amount;
        String symbol;
        ExchangeDirection direction = ExchangeDirection.sell;
        ExchangeRemain remain = ExchangeRemain.builder().build();
        ExchangeExcessProtect excessProtect = ExchangeExcessProtect.fix_input;
        ExchangeMinToReceive minToReceive;
        ExchangeHybrid hybrid;
        boolean logs = false;
        int pct = GolosConstants.STEEMIT_100_PERCENT;
        //boolean pctDirect = false;
        Set<String> hiddenAssets;
}
