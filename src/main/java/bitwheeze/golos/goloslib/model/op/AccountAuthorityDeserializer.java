package bitwheeze.golos.goloslib.model.op;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountAuthorityDeserializer extends StdDeserializer<AccountAuthority> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccountAuthorityDeserializer() {
        this(null);
    }
  
    public AccountAuthorityDeserializer(Class<AccountAuthority> t) {
        super(t);
    }

    @Override
    public AccountAuthority deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        log.debug("parse AccountAuthority");
        
        JsonNode node = p.readValueAsTree();
        if(!node.isArray()) {
            throw new RuntimeException("AccountAuthority as an array expected " + node.toString());
        }
        if(node.size() != 2) {
            throw new RuntimeException("AccountAuthority as an array of 2 elements (String, Integer) expected, actual " + node.size());
        }
        
        String account = node.get(0).asText();
        Integer threshold = node.get(1).asInt();
        
        return new AccountAuthority(account, threshold);
    }
}
