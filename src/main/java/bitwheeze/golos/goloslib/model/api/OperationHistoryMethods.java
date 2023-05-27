package bitwheeze.golos.goloslib.model.api;


public class OperationHistoryMethods extends ApiMethod {

    public static ApiMethod getOpsInBlock() {return new OperationHistoryMethods("get_ops_in_block", 2);};

    public OperationHistoryMethods(String method, String [] paramNames) {
        super("operation_history", method, paramNames);
    }

    public OperationHistoryMethods(String method, int paramCount) {
        super("operation_history", method, paramCount);
    }

    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
