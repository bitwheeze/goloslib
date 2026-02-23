package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringArrayDeserializer;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("donate")
@AllArgsConstructor
@Builder
public class Donate extends Operation {
    private String from;
    private String to;
    private Asset amount;
    private DonateMemo memo;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
