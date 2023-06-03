package bitwheeze.golos.goloslib.model.api;


public class WitnessMethods extends ApiMethod {

    public static WitnessMethods getCurrentMedianHistoryPrice() {return new WitnessMethods( "get_current_median_history_price", new String [] {});};


    public WitnessMethods(String method, String [] paramNames) {
        super("witness_api", method, paramNames);
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
