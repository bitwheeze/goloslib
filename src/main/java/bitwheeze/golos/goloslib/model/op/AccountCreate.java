package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("account_create")
public class AccountCreate extends Operation {
    private Asset fee;
    private String creator;
    private String newAccountName;
    private Authority owner;
    private Authority active;
    private Authority posting;
    private String memoKey;
    private String jsonMetadata;
}


