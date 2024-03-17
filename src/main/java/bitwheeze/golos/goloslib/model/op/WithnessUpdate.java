package bitwheeze.golos.goloslib.model.op;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.ToStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@OpName("witness_update")
@AllArgsConstructor
@NoArgsConstructor
public class WithnessUpdate extends Operation {
    String owner;
    String url;
    String blockSigningKey;
    @JsonDeserialize(using = ToStringDeserializer.class)
    String props;
    Asset fee;
}
