package bitwheeze.golos.goloslib.model.api;


public class NetworkBroadcastMethods extends ApiMethod {

    public static ApiMethod broadcastTransaction() {return new ApiMethod("network_broadcast_api", "broadcast_transaction", 1);};


    public NetworkBroadcastMethods(String api, String method, String [] paramNames) {

        super(api, method, paramNames);
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
