package bitwheeze.golos.goloslib.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ApiMethod {

    private String id = "0";
    private final String jsonrpc = "2.0";
    private final String method = "call"; //: "call" //TODO: Types?
    private final Object[] params = new Object[3];
    private final String[] paramNames;

    public ApiMethod(String api, String method, int parameterCount) {
        this.params[0] = api;
        this.params[1] = method;
        this.params[2] = new Object [parameterCount];
        this.paramNames = new String[] {};
    }

    public ApiMethod(String api, String method, String[] paramNames) {
        this.params[0] = api;
        this.params[1] = method;
        this.params[2] = new Object [paramNames.length];
        this.paramNames = paramNames;
    }

    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
    
    @JsonIgnore
    public Object[] getMethodParams() {
        return (Object[]) params[2];
    }
}
