package bitwheeze.golos.goloslib.model;

import bitwheeze.golos.goloslib.AssetDefinition;
import bitwheeze.golos.goloslib.model.exception.BlockchainError;
import bitwheeze.golos.goloslib.model.exception.NodeError;
import bitwheeze.golos.goloslib.model.exchange.ExchangeResult;
import bitwheeze.golos.goloslib.model.op.virtual.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@ToString
public class ApiResponse<T> {
    public static class Empty {}
    
    @Setter
    String jsonrpc;
    @Setter
    T result;

    @Setter
    @Getter
    NodeError error;

    @Setter
    @Getter
    String id;
    
    public boolean isError() {
        return (error != null);
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(result);
    }
    
    public T orElseThrow() {
        if (result == null) {
            throw new BlockchainError(error);
        }
        return result;
    }
    
    public <E> Optional<E> map(Function<? super T, ? extends E> mapper) {
        Objects.requireNonNull(mapper);
        if (result == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(mapper.apply(result));
        }
    }
    
    public void ifPresent(Consumer<? super T> action) {
        if (result != null) {
            action.accept(result);
        }
    }    
    
    public static class EmptyResponse extends ApiResponse<Empty> {}
    public static class DynamicGlobalPropertiesResponse extends ApiResponse<DynamicGlobalProperties> {}
    public static class BlockResponse extends ApiResponse<Block> {}
    public static class AccountsResponse extends ApiResponse<List<Account>> {}
    public static class OpsInBlockResponse extends ApiResponse<List<OperationHistoryRecord>> {}
    public static class AccountNamesResponse extends ApiResponse<List<String>> {}
    public static class StringResponse extends ApiResponse<String> {}
    public static class AccountsBalancesResponse extends ApiResponse<List<HashMap<String, UiaBalances>>> {}
    public static class AssetDefinitionResponse extends ApiResponse<List<AssetDefinition>> {}
    public static class ContentResponse extends ApiResponse<Content> {}
    public static class ChainPropertiesResponse extends ApiResponse<ChainProperties> {}
    public static class ConfigResponse extends ApiResponse<Config> {}


    //TODO: move to corresponding api blocks
    public static class OpenOrdersResponse extends ApiResponse<List<OpenOrder>> {}
    public static class OrderBookResponse extends ApiResponse<OrderBook> {}
    public static class MedianHistoryPriceResponse extends ApiResponse<Price> {};

    public static class GetEventsInBlockResponse extends ApiResponse<List<Event>> {};

    public static class VestingDelegationResponse extends ApiResponse<List<VestingDelegation>> {};

    public static class GetExchangeResponse extends ApiResponse<ExchangeResult> {};
}
