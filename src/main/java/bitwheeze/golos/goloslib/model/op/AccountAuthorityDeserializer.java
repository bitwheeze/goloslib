package bitwheeze.golos.goloslib.model.op;

import java.io.IOException;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountAuthorityDeserializer extends StdDeserializer<AccountAuthority> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccountAuthorityDeserializer() {
        this(AccountAuthority.class);
    }
  
    public AccountAuthorityDeserializer(Class<AccountAuthority> t) {
        super(t);
    }

    @Override
    public AccountAuthority deserialize(JsonParser p, DeserializationContext ctxt)
            {
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
