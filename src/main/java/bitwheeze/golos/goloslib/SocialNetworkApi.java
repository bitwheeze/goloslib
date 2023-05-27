package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.model.api.ApiMethod;
import bitwheeze.golos.goloslib.model.api.SocialNetworkMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SocialNetworkApi extends GolosApiReactive {
    public SocialNetworkApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }

    public Mono<ApiResponse.ContentResponse> getContent(String account, String permlink, int voteLimit, int voteOffset) {
        var method = SocialNetworkMethods.getContent();
        method.getMethodParams()[0] = account;
        method.getMethodParams()[1] = permlink;
        method.getMethodParams()[2] = voteLimit;
        method.getMethodParams()[3] = voteOffset;

        return send(method, ApiResponse.ContentResponse.class);
    }
}
