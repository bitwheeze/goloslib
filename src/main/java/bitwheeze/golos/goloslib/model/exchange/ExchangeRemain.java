package bitwheeze.golos.goloslib.model.exchange;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ExchangeRemain {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExchangeRemainPolicy direct;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExchangeRemainPolicy multi;
}
