package bitwheeze.golos.goloslib.model;

import java.time.LocalDateTime;

import bitwheeze.golos.goloslib.model.op.Operation;
import lombok.Data;

@Data
public class OperationHistoryRecord {
    private String trxId;
    private long block;
    private int trxInBlock;
    private int opInTrx;
    private int virtualOp;
    private LocalDateTime timestamp;
    private Operation op;
}
