package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@JsonDeserialize(using = KeyAuthorityDeserializer.class) 
public class KeyAuthority {
    private String publicKey;
    private int threshold;   
}
