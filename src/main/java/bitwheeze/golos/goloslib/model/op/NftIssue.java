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
@OpName("nft_issue")
@AllArgsConstructor
public class NftIssue extends Operation {
    private String creator;
    private String name;
    private String to;
    private String jsonMetadata;
    @JsonDeserialize(using = ToStringArrayDeserializer.class)
    private String[] extensions;
}
