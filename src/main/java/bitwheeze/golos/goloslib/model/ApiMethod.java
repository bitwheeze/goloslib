package bitwheeze.golos.goloslib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ApiMethod {

    public static ApiMethod getDynamicGlobalProperties = new ApiMethod("database_api", "get_dynamic_global_properties", 0);
    public static ApiMethod getBlock = new ApiMethod("database_api", "get_block", 1);
    public static ApiMethod getAccounts = new ApiMethod("database_api", "get_accounts", 1);
    public static ApiMethod getAccountsBalances = new ApiMethod("database_api", "get_accounts_balances", 1);
    public static ApiMethod getOpsInBlock = new ApiMethod("operation_history", "get_ops_in_block", 2);
    public static ApiMethod broadcastTransaction = new ApiMethod("network_broadcast_api", "broadcast_transaction", 1);
    public static ApiMethod lookupAccounts = new ApiMethod("database_api", "lookup_accounts", 2);
    public static ApiMethod getTransactionHex = new ApiMethod("database_api", "get_transaction_hex", 1);
    public static ApiMethod getAssets = new ApiMethod("database_api", "get_assets", 5);
    public static ApiMethod getContent = new ApiMethod("social_network", "get_content", 4);
    public static ApiMethod getChainProperties = new ApiMethod("database_api", "get_chain_properties", 0);
    public static ApiMethod getConfig = new ApiMethod("database_api", "get_config", 0);

    private String id = "0";
    private final String jsonrpc = "2.0";
    private final String method = "call"; //: "call" //TODO: Types?
    private final Object[] params = new Object[3];
    
    public ApiMethod(String api, String method, int parameterCount) {
        this.params[0] = api;
        this.params[1] = method;
        this.params[2] = new Object [parameterCount]; 
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
    
    @JsonIgnore
    public Object[] getMethodParams() {
        return (Object[]) params[2];
    }
}
