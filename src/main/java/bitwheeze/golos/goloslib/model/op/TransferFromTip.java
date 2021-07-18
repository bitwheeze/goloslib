package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@OpName("transfer_from_tip")
public class TransferFromTip extends Operation {
    private String from;
    private String to;
    private Asset amount;
    private String memo;
    @JsonDeserialize(using = ToStringDeserializer.class)
    private String extensions;
}
