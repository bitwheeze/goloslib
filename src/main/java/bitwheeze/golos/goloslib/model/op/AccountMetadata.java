package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = JsonDeserializer.None.class)
@OpName("account_metadata")
public class AccountMetadata extends Operation {
    private String account;
    private String jsonMetadata;
}


