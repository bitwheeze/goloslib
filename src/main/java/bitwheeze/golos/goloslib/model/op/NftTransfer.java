package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("nft_transfer")
@AllArgsConstructor
public class NftTransfer extends Operation {
    private int tokenId;
    private String from;
    private String to;
    private String memo;
    private String[] extensions;
}
