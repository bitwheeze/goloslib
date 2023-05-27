package bitwheeze.golos.goloslib.model.api;


public class DatabaseMethods extends ApiMethod {

    public static ApiMethod getDynamicGlobalProperties() {return new DatabaseMethods("get_dynamic_global_properties", 0);};
    public static ApiMethod getBlock() {return new DatabaseMethods("get_block", 1);};
    public static ApiMethod getAccounts() {return new DatabaseMethods("get_accounts", 1);};
    public static ApiMethod getAccountsBalances() {return new DatabaseMethods("get_accounts_balances", 1);};
    public static ApiMethod lookupAccounts() {return new DatabaseMethods("lookup_accounts", 2);};
    public static ApiMethod getTransactionHex() {return new DatabaseMethods("get_transaction_hex", 1);};
    public static ApiMethod getAssets() {return new DatabaseMethods("get_assets", 5);};
    public static ApiMethod getChainProperties() {return new DatabaseMethods("get_chain_properties", 0);};
    public static ApiMethod getConfig() {return new DatabaseMethods("get_config", 0);};


    public DatabaseMethods(String method, String [] paramNames) {
        super("database_api", method, paramNames);
    }

    public DatabaseMethods(String method, int paramCount) {
        super("database_api", method, paramCount);
    }

    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
