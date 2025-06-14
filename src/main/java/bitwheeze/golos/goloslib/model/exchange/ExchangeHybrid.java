package bitwheeze.golos.goloslib.model.exchange;

import bitwheeze.golos.goloslib.model.GolosConstants;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class ExchangeHybrid {
    ExchangeHybridStrategy strategy;
    int discreteStep = GolosConstants.GOLOS_MIN_DISCRETE_STEP;
    boolean fixSell;
}
