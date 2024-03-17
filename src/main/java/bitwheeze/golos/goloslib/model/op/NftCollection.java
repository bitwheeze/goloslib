package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("nft_collection")
@AllArgsConstructor
public class NftCollection extends Operation {
    private String creator;
    private String name;
    private String jsonMetadata;
    private int maxTokenCount;
    private String[] extensions;
}
