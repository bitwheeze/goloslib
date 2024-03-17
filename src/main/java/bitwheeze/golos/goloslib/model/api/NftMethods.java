package bitwheeze.golos.goloslib.model.api;


public class NftMethods extends ApiMethod {

    public static NftMethods getNftTokens() {return new NftMethods("get_nft_tokens", new String[]{"query"});};


    public NftMethods(String method, String [] paramNames) {

        super("nft_api", method, paramNames);
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
