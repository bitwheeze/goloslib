package bitwheeze.golos.goloslib.model.api;


public class SocialNetworkMethods extends ApiMethod {

    public static ApiMethod getContent() {return new SocialNetworkMethods("get_content", 4);};


    public SocialNetworkMethods(String method, String [] paramNames) {
        super("social_network", method, paramNames);
    }

    public SocialNetworkMethods(String method, int paramCount) {
        super("social_network", method, paramCount);
    }

    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
