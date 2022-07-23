package bitwheeze.golos.goloslib.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Block {
    private String previous;
    private LocalDateTime timestamp;
    private String witness;
    private String transactionMerkleRoot;
    @JsonDeserialize(using = ToStringDeserializer.class)    
    private String extensions; //TODO:
    private String witnessSignature;
    private Transaction[] transactions;
    private long timestampMsec;
    private long requestTimeMsec;
}
