package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.ToStringArrayDeserializer;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = ValueDeserializer.None.class)
@OpName("nft_transfer")
@AllArgsConstructor
public class NftTransfer extends Operation {
    private int tokenId;
    private String from;
    private String to;
    private String memo;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
