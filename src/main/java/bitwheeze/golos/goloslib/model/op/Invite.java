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
@OpName("invite")
@AllArgsConstructor
@NoArgsConstructor
public class Invite extends Operation {
    String creator;
    Asset balance;
    String inviteKey;
    @JsonDeserialize(using = ToStringDeserializer.class)
    private String extensions;
}
