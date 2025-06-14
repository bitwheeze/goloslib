package bitwheeze.golos.goloslib.model.api;


public class ExchangeMethods extends ApiMethod {

    public static ExchangeMethods getExchangePath() {return new ExchangeMethods( "get_exchange_path", new String [] {"query"});};
    public static ExchangeMethods getExchange() {return new ExchangeMethods( "get_exchange", new String [] {"query"});};

    public ExchangeMethods(String method, String [] paramNames) {
        super("exchange", method, paramNames);
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
