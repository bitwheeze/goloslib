package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.ApiMethod;
import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class GolosApi {

    @Autowired
    @Qualifier("golos_api")
    WebClient client;

    private <T> T send(ApiMethod method, Class<T> requestClass) {
        return client
                .post()
                .body(Mono.just(method), ApiMethod.class)
                .retrieve()
                .bodyToMono(requestClass)
                .block();
    }

    private <T> Mono<T> sendMono(ApiMethod method, Class<T> requestClass) {
        return client
                .post()
                .body(Mono.just(method), ApiMethod.class)
                .retrieve()
                .bodyToMono(requestClass);
    }

    public ApiResponse.DynamicGlobalPropertiesResponse getDynamicGlobalProperties() {
        var method = ApiMethod.getDynamicGlobalProperties;
        return send(method, ApiResponse.DynamicGlobalPropertiesResponse.class);
    }

    public ApiResponse.BlockResponse getBlock(long blockNum) {
        var method = ApiMethod.getBlock;
        method.getMethodParams()[0] = blockNum;
        return send(method, ApiResponse.BlockResponse.class);
    }
    
    public Optional<Account> getAccount(String account) {
        return getAccounts(new String [] {account}).orElseThrow().stream().findAny();
    }
    
    public ApiResponse.AccountsResponse getAccounts(String [] accounts) {
        var method = ApiMethod.getAccounts;
        method.getMethodParams()[0] = accounts;
        return send(method, ApiResponse.AccountsResponse.class);
    }

    public Mono<ApiResponse.AccountsResponse> getAccountsMono(String [] accounts) {
        var method = ApiMethod.getAccounts;
        method.getMethodParams()[0] = accounts;
        return sendMono(method, ApiResponse.AccountsResponse.class);
    }

    public ApiResponse.AccountsResponse getAccounts(List<String> accounts) {
        var method = ApiMethod.getAccounts;
        method.getMethodParams()[0] = accounts;
        return send(method, ApiResponse.AccountsResponse.class);
    }

    public ApiResponse.OpsInBlockResponse getOpsInBlock(long blockNum, boolean onlyVirtual) {
        var method = ApiMethod.getOpsInBlock;
        method.getMethodParams()[0] = blockNum;
        method.getMethodParams()[1] = onlyVirtual;
        return send(method, ApiResponse.OpsInBlockResponse.class);
    }
    
    public ApiResponse.EmptyResponse broadcastTransaction(Transaction tr) {
        var method = ApiMethod.broadcastTransaction;
        method.getMethodParams()[0] = tr;
        return send(method, ApiResponse.EmptyResponse.class);
    }
    
    public ApiResponse.AccountNamesResponse lookupAccounts(String lowerBoundName, int limit) {
        var method = ApiMethod.lookupAccounts;
        method.getMethodParams()[0] = lowerBoundName;
        method.getMethodParams()[1] = limit;
        return send(method, ApiResponse.AccountNamesResponse.class);
    }

    public ApiResponse.StringResponse getTransactionHex(Transaction tr) {
        var method = ApiMethod.getTransactionHex;
        method.getMethodParams()[0] = tr;
        return send(method, ApiResponse.StringResponse.class);
    }
}
