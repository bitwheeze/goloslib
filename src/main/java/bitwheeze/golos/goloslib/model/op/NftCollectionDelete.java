package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.ToStringArrayDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("nft_collection_delete")
@AllArgsConstructor
public class NftCollectionDelete extends Operation {
    private String creator;
    private String name;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
