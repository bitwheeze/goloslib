package bitwheeze.golos.goloslib.model.op;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountCreateWithDelegationExtensionDeserializer extends StdDeserializer<AccountCreateWithDelegationExtension> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccountCreateWithDelegationExtensionDeserializer() {
        this(null);
    }
  
    public AccountCreateWithDelegationExtensionDeserializer(Class<AccountCreateWithDelegationExtension> t) {
        super(t);
    }

    @Override
    public AccountCreateWithDelegationExtension deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
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
                ext.setAccountReferralOptions(p.getCodec().treeToValue(extensionRaw, AccountReferralOptions.class));
                break;
            }
        }
        
        return ext;
    }
}
