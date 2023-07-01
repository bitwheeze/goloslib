package bitwheeze.golos.goloslib.model.op.virtual;

import bitwheeze.golos.goloslib.model.op.Operation;
import bitwheeze.golos.goloslib.model.op.OperationPack;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Event {
    String trxId;
    long block;
    int trxInBlock;
    int virtualOp;
    LocalDateTime timestamp;
    OperationPack op;
}
