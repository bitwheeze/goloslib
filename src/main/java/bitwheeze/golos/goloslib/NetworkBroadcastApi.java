package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.model.api.ApiMethod;
import bitwheeze.golos.goloslib.model.api.NetworkBroadcastMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class NetworkBroadcastApi extends GolosApiReactive {


    public NetworkBroadcastApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }


    public Mono<ApiResponse.EmptyResponse> broadcastTransaction(Transaction tr) {
        var method = NetworkBroadcastMethods.broadcastTransaction();
        method.getMethodParams()[0] = tr;
        return send(method, ApiResponse.EmptyResponse.class);
    }
}
