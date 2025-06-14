package bitwheeze.golos.goloslib.model.exchange;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonDeserialize(using = ExchangeResultDeserializer.class)
public class ExchangeResult {
    ExChain direct;
    ExChain best;
    ExChain[] allChains;
}
