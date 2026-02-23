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
@OpName("nft_collection_delete")
@AllArgsConstructor
public class NftCollectionDelete extends Operation {
    private String creator;
    private String name;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
