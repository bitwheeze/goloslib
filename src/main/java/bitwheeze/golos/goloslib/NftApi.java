package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.api.MarketHistoryMethods;
import bitwheeze.golos.goloslib.model.api.NftMethods;
import bitwheeze.golos.goloslib.model.nft.NftResponses;
import bitwheeze.golos.goloslib.model.nft.NftTokenQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class NftApi extends GolosApiReactive {


    public NftApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }

    public Mono<NftResponses.GetNftTokenResponse> getNftToken(NftTokenQuery query) {
        var method = NftMethods.getNftTokens();
        method.getMethodParams()[0] = query;
        return send(method, NftResponses.GetNftTokenResponse.class);
    }
}
