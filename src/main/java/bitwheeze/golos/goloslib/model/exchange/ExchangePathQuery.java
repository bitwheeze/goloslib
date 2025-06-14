package bitwheeze.golos.goloslib.model.exchange;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@ToString
public class ExchangePathQuery {
        String sell;
        String buy;
        Set<String> selectSyms;
        Set<String> filterSyms;
        boolean assets;
}
