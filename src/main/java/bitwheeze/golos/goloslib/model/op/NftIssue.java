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
