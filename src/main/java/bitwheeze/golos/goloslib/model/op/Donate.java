package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("donate")
@AllArgsConstructor
@Builder
public class Donate extends Operation {
    private String from;
    private String to;
    private Asset amount;
    private DonateMemo memo;
    private String[] extensions;
}
