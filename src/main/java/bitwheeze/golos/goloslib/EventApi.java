package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.api.EventMethods;
import bitwheeze.golos.goloslib.model.api.MarketHistoryMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class EventApi extends GolosApiReactive {


    public EventApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }

    public Mono<ApiResponse.GetEventsInBlockResponse> getEventsInBlock(long blockNum, boolean onlyVirtual) {
        var method = EventMethods.getEventsInBlock();
        method.getMethodParams()[0] = blockNum;
        method.getMethodParams()[1] = onlyVirtual;
        return send(method, ApiResponse.GetEventsInBlockResponse.class);
    }
}
