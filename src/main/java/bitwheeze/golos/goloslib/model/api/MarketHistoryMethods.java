package bitwheeze.golos.goloslib.model.api;

import lombok.Getter;


public class MarketHistoryMethods extends ApiMethod {

    public static MarketHistoryMethods getOpenOrders() {return new MarketHistoryMethods( "get_open_orders", new String [] {"owner", "pair"});};


    public MarketHistoryMethods(String method, String [] paramNames) {
        super("market_history", method, paramNames);
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
