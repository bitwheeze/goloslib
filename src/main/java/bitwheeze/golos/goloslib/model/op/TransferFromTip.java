package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringArrayDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@OpName("transfer_from_tip")
@AllArgsConstructor
@Builder
public class TransferFromTip extends Operation {
    private String from;
    private String to;
    private Asset amount;
    private String memo;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
