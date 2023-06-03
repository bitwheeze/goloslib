package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.api.MarketHistoryMethods;
import bitwheeze.golos.goloslib.model.api.WitnessMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class WitnessApi extends GolosApiReactive {


    public WitnessApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }

    public Mono<ApiResponse.MedianHistoryPriceResponse> getGetCurrentMedianHistoryPrice() {
        var method = WitnessMethods.getCurrentMedianHistoryPrice();
        return send(method, ApiResponse.MedianHistoryPriceResponse.class);
    }
}
