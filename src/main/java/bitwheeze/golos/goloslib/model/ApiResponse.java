package bitwheeze.golos.goloslib.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiResponse<T> {
    public static class Empty {}
    
    String jsonrpc;
    T result;
    @JsonDeserialize(using = ToStringDeserializer.class)
    String error;
    
    public boolean isError() {
        return (error != null);
    }
    
    public static class EmptyResponse extends ApiResponse<Empty> {}
    public static class DynamicGlobalPropertiesResponse extends ApiResponse<DynamicGlobalProperties> {}
    public static class BlockResponse extends ApiResponse<Block> {}
    public static class OpsInBlockResponse extends ApiResponse<List<OperationHistoryRecord>> {}
}
