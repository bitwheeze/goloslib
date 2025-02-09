package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.*;
import bitwheeze.golos.goloslib.model.api.MarketHistoryMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MarketHistoryApi extends GolosApiReactive {


    public MarketHistoryApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }

    public Mono<ApiResponse.OpenOrdersResponse> getOpenOrders(String account, String base, String quote) {
        var method = MarketHistoryMethods.getOpenOrders();
        method.getMethodParams()[0] = account;
        method.getMethodParams()[1] = new String [] {base, quote};
        return send(method, ApiResponse.OpenOrdersResponse.class);
    }

    public Mono<ApiResponse.OrderBookResponse> getOrderBook(String base, String quote, int limit) {
        var method = MarketHistoryMethods.getOrderBook();
        method.getMethodParams()[0] = limit;
        method.getMethodParams()[1] = new String [] {base, quote};
        return send(method, ApiResponse.OrderBookResponse.class);
    }
}
