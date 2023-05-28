package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.api.ApiMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class GolosApiReactive {

    final WebClient client;

    public GolosApiReactive(WebClient client) {
        this.client = client;
    }

    public <T> Mono<T> send(ApiMethod method, Class<T> requestClass) {
        return client
                .post()
                .body(Mono.just(method), ApiMethod.class)
                .retrieve()
                .bodyToMono(requestClass);
    }
}
