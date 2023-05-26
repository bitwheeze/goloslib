package bitwheeze.golos.goloslib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;


public class MarketHistoryMethods extends ApiMethod {

    @Getter
    private final String[] paramNames;

    public static MarketHistoryMethods getOpenOrders() {return new MarketHistoryMethods("market_history", "get_open_orders", new String [] {"owner", "pair"});};


    public MarketHistoryMethods(String api, String method, String [] paramNames) {

        super(api, method, paramNames.length);

        this.paramNames = paramNames;
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
