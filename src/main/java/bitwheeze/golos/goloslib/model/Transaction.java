package bitwheeze.golos.goloslib.model;

import java.time.LocalDateTime;

import bitwheeze.golos.goloslib.model.op.OperationPack;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Transaction {
    private long refBlockNum;
    private long refBlockPrefix;
    private LocalDateTime expiration;    
    private OperationPack[] operations;
    //Not used currently
    private final TransactionExtension[] extensions = new TransactionExtension[0];
    private String [] signatures;
}
