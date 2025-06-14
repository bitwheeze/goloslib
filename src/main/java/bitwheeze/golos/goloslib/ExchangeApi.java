package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.api.ExchangeMethods;
import bitwheeze.golos.goloslib.model.api.MarketHistoryMethods;
import bitwheeze.golos.goloslib.model.exchange.ExchangeQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExchangeApi extends GolosApiReactive {


    public ExchangeApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }

    public Mono<ApiResponse.GetExchangeResponse> getExchange(ExchangeQuery query) {
        var method = ExchangeMethods.getExchange();
        method.getMethodParams()[0] = query;
        return send(method, ApiResponse.GetExchangeResponse.class);
    }


    /*
    public Mono<ApiResponse.OrderBookResponse> getOrderBook(String base, String quote, int limit) {
        var method = MarketHistoryMethods.getOrderBook();
        method.getMethodParams()[0] = limit;
        method.getMethodParams()[1] = new String [] {base, quote};
        return send(method, ApiResponse.OrderBookResponse.class);
    }

     */
}
