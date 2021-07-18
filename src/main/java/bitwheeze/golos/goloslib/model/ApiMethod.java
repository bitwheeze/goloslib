package bitwheeze.golos.goloslib.model;

import lombok.Data;

@Data
public class ApiMethod {
    private long id = 0;
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
    
    public Object[] getMethodParams() {
        return (Object[]) params[2];
    }
}
