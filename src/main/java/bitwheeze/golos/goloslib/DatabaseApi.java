package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.api.ApiMethod;
import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.model.api.DatabaseMethods;
import bitwheeze.golos.goloslib.model.api.NetworkBroadcastMethods;
import bitwheeze.golos.goloslib.types.DelegationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DatabaseApi extends GolosApiReactive {


    public DatabaseApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }

    public Mono<ApiResponse.DynamicGlobalPropertiesResponse> getDynamicGlobalProperties() {
        var method = DatabaseMethods.getDynamicGlobalProperties();
        return send(method, ApiResponse.DynamicGlobalPropertiesResponse.class);
    }

    public Mono<ApiResponse.BlockResponse> getBlock(long blockNum) {
        var method = DatabaseMethods.getBlock();
        method.setId(String.valueOf(blockNum));
        method.getMethodParams()[0] = blockNum;
        return send(method, ApiResponse.BlockResponse.class);
    }
    
    public Mono<Optional<Account>> getAccount(String account) {
        return getAccounts(new String [] {account})
                .map(accountsResponse -> accountsResponse
                        .orElseThrow().stream().findAny());
    }
    
    public Mono<ApiResponse.AccountsResponse> getAccounts(String [] accounts) {
        var method = DatabaseMethods.getAccounts();
        method.getMethodParams()[0] = accounts;
        return send(method, ApiResponse.AccountsResponse.class);
    }

    public Mono<ApiResponse.AccountsResponse> getAccounts(List<String> accounts) {
        var method = DatabaseMethods.getAccounts();
        method.getMethodParams()[0] = accounts;
        return send(method, ApiResponse.AccountsResponse.class);
    }

    public Mono<ApiResponse.AccountsBalancesResponse> getAccountsBalances(String [] accounts) {
        var method = DatabaseMethods.getAccountsBalances();
        method.getMethodParams()[0] = accounts;
        return send(method, ApiResponse.AccountsBalancesResponse.class);
    }

    public Mono<ApiResponse.AccountNamesResponse> lookupAccounts(String lowerBoundName, int limit) {
        var method = DatabaseMethods.lookupAccounts();
        method.getMethodParams()[0] = lowerBoundName;
        method.getMethodParams()[1] = limit;
        return send(method, ApiResponse.AccountNamesResponse.class);
    }

    public Mono<ApiResponse.StringResponse> getTransactionHex(Transaction tr) {
        var method = DatabaseMethods.getTransactionHex();
        method.getMethodParams()[0] = tr;
        return send(method, ApiResponse.StringResponse.class);
    }

    public Mono<ApiResponse.AssetDefinitionResponse> getAssets(String creator, String [] symbols, String from, int limit, String sort) {
        var method = DatabaseMethods.getAssets();
        method.getMethodParams()[0] = Optional.ofNullable(creator).orElse("");
        method.getMethodParams()[1] = Optional.ofNullable(symbols).orElse(new String [0]);
        method.getMethodParams()[2] = Optional.ofNullable(from).orElse("");
        method.getMethodParams()[3] = Optional.of(String.valueOf(limit)).orElse("20");
        method.getMethodParams()[4] = Optional.ofNullable(sort).orElse("by_symbol_name");
        return send(method, ApiResponse.AssetDefinitionResponse.class);
    }

    public Mono<ApiResponse.ChainPropertiesResponse> getChainProperties() {
        return send(DatabaseMethods.getChainProperties(), ApiResponse.ChainPropertiesResponse.class);
    }

    public Mono<ApiResponse.ConfigResponse> getConfig() {
        return send(DatabaseMethods.getConfig(), ApiResponse.ConfigResponse.class);
    }

    public Mono<ApiResponse.VestingDelegationResponse> getVestingDelegations(String account, String from, Integer limit, DelegationType type) {
        var method = DatabaseMethods.getVestingDelegations();
        method.getMethodParams()[0] = account;
        method.getMethodParams()[1] = Optional.ofNullable(from).orElse("");;
        method.getMethodParams()[2] = Optional.ofNullable(limit).orElse(10);
        method.getMethodParams()[3] = type;

        return send(method, ApiResponse.VestingDelegationResponse.class);
    }

}
