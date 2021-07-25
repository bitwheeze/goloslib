package bitwheeze.golos.goloslib.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Uint16 {
    private int value;
    public int intValue() {
        return value;
    }
    
    @Override
    @JsonProperty
    public String toString() {
        return String.valueOf(value);
    }
    
    @JsonProperty
    public void fromString(String strValue) {
        this.value = Integer.valueOf(strValue);
    }
}
