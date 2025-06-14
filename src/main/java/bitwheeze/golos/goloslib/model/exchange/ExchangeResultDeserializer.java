package bitwheeze.golos.goloslib.model.exchange;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ExchangeResultDeserializer extends JsonDeserializer<ExchangeResult> {

    @Override
    public ExchangeResult deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        ExchangeResult result = new ExchangeResult();

        JsonNode directNode = node.get("direct");
        if (directNode != null) {
            if (directNode.isBoolean() && !directNode.asBoolean()) {
                result.setDirect(null);
            } else {
                result.setDirect(mapper.treeToValue(directNode, ExChain.class));
            }
        }

        JsonNode bestNode = node.get("best");
        if (bestNode != null) {
            if (bestNode.isBoolean() && !bestNode.asBoolean()) {
                result.setBest(null);
            } else {
                result.setBest(mapper.treeToValue(bestNode, ExChain.class));
            }
        }

        JsonNode allChains = node.get("all_chains");
        if (bestNode != null) {
            result.setAllChains(mapper.treeToValue(allChains, ExChain[].class));
        }

        return result;
    }
}