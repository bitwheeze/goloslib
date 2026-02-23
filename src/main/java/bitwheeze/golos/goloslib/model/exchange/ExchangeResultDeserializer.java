package bitwheeze.golos.goloslib.model.exchange;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ValueDeserializer;

import java.io.IOException;
import java.util.List;

public class ExchangeResultDeserializer extends ValueDeserializer<ExchangeResult> {

    @Override
    public ExchangeResult deserialize(JsonParser p, DeserializationContext ctxt) {
        ObjectMapper mapper = (ObjectMapper) p.objectReadContext();
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
