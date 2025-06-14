package bitwheeze.golos.goloslib.model.exchange;

import bitwheeze.golos.goloslib.model.Asset;

public record ExchangeMinToReceive(
        Asset direct,
        Asset multi,
        int minProfitPct
    ) {
}
