package bitwheeze.golos.goloslib;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.ApiMethod;
import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.resource.Methods;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GolosApi {

    @Autowired
    Methods methods;
    
    @Autowired
    WebClient client;
    
    private <T> T send(ApiMethod method, Class<T> requestClass) {
        return client
                .post()
                .body(Mono.just(method), ApiMethod.class)
                .retrieve()
                .bodyToMono(requestClass)
                .block();
        
    }
    
    public ApiResponse.DynamicGlobalPropertiesResponse getDynamicGlobalProperties() {
        var method = methods.getApiMethod("database_api", "get_dynamic_global_properties");
        
        return send(method, ApiResponse.DynamicGlobalPropertiesResponse.class);
    }

    public ApiResponse.BlockResponse getBlock(long blockNum) {
        var method = methods.getApiMethod("database_api", "get_block");
        method.getMethodParams()[0] = blockNum;
        return send(method, ApiResponse.BlockResponse.class);
    }
    
    public Account getAccount(String account) {
        return getAccounts(new String [] {account}).orElseThrow().stream().findAny().orElse(null);
    }
    
    public ApiResponse.AccountsResponse getAccounts(String [] accounts) {
        var method = methods.getApiMethod("database_api", "get_accounts");
        method.getMethodParams()[0] = accounts;
        return send(method, ApiResponse.AccountsResponse.class);
    }

    public ApiResponse.AccountsResponse getAccounts(List<String> accounts) {
        var method = methods.getApiMethod("database_api", "get_accounts");
        method.getMethodParams()[0] = accounts;
        return send(method, ApiResponse.AccountsResponse.class);
    }

    public ApiResponse.OpsInBlockResponse getOpsInBlock(long blockNum, boolean onlyVirtual) {
        var method = methods.getApiMethod("operation_history", "get_ops_in_block");
        method.getMethodParams()[0] = blockNum;
        method.getMethodParams()[1] = onlyVirtual;
        return send(method, ApiResponse.OpsInBlockResponse.class);
    }
    
    public ApiResponse.EmptyResponse broadcastTransaction(Transaction tr) {
        var method = methods.getApiMethod("network_broadcast_api", "broadcast_transaction");
        method.getMethodParams()[0] = tr;
        return send(method, ApiResponse.EmptyResponse.class);
    }
    
    public ApiResponse.AccountNamesResponse lookupAccounts(String lowerBoundName, int limit) {
        var method = methods.getApiMethod("database_api", "lookup_accounts");
        method.getMethodParams()[0] = lowerBoundName;
        method.getMethodParams()[1] = limit;
        return send(method, ApiResponse.AccountNamesResponse.class);
    }
}
