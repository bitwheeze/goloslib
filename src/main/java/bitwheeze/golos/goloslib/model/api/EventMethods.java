package bitwheeze.golos.goloslib.model.api;


public class EventMethods extends ApiMethod {

    public static EventMethods getEventsInBlock() {return new EventMethods( "get_events_in_block", new String [] {"blockNum", "onlyVirtual"});};


    public EventMethods(String method, String [] paramNames) {
        super("event_plugin", method, paramNames);
    }
    
    public static void main(String[] args) {
        //ApiRequest req = new ApiRequest("database_api", "get_dynamic_global_properties")
    }
}
