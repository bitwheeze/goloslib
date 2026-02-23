package bitwheeze.golos.goloslib.model.op;

import java.io.IOException;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.deser.std.StdDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountCreateWithDelegationExtensionDeserializer extends StdDeserializer<AccountCreateWithDelegationExtension> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccountCreateWithDelegationExtensionDeserializer() {
        this(AccountCreateWithDelegationExtension.class);
    }
  
    public AccountCreateWithDelegationExtensionDeserializer(Class<AccountCreateWithDelegationExtension> t) {
        super(t);
    }

    @Override
    public AccountCreateWithDelegationExtension deserialize(JsonParser p, DeserializationContext ctxt)
            {
        log.debug("parse AccountCreateWithDelegationExtension");
        
        JsonNode node = p.readValueAsTree();
        if(!node.isArray()) {
            throw new RuntimeException("AccountCreateWithDelegationExtension as an array expected " + node.toString());
        }

        var ext = new AccountCreateWithDelegationExtension();
        
        int extensionsCount = node.size();
        for(int ei = 0; ei < extensionsCount; ei++) {
            JsonNode extensionNode = node.get(ei);
            //is an array
            int extensionKey = extensionNode.get(0).asInt();
            JsonNode extensionRaw = extensionNode.get(1);
            switch(extensionKey) {
            case 0:
                ext.setAccountReferralOptions(((ObjectMapper) p.objectReadContext()).treeToValue(extensionRaw, AccountReferralOptions.class));
                break;
            }
        }
        
        return ext;
    }
}
