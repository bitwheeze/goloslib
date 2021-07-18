package bitwheeze.golos.goloslib;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.model.op.Operation;
import bitwheeze.golos.goloslib.model.op.OperationPack;
import bitwheeze.golos.goloslib.resource.Signature;

@Component
@Scope("prototype")
public class TransactionBuilder {
    
    @Autowired
    private GolosApi api;
    
    @Autowired
    private Signature sign;
    
    @Value("${golos.tr.expiration_delay:300}")
    private long expiration_delay;
    private long refBlockNum = 49186;
    private long refBlockPrefix = 2378203595l;
    private LocalDateTime expiration;
    
    private final List<Operation> operations = new ArrayList<>();
    
    public TransactionBuilder add(Operation op) {
        this.operations.add(op);
        return this;
    }
    
    public TransactionBuilder refBlockNum(long refBlockNum) {
        this.refBlockNum = refBlockNum;
        return this;
    }
    
    public TransactionBuilder refBlockPrefix(long refBlockPrefix) {
        this.refBlockPrefix = refBlockPrefix;
        return this;
    }
    
    public TransactionBuilder expiration(LocalDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    public Transaction sign(String [] keys) {
        if(operations.isEmpty()) {
            throw new RuntimeException("No operations added to transaction!");
        }
        Transaction tr = new Transaction();
        
        packOperations(tr);
        
        tr.setRefBlockNum(this.refBlockNum);
        tr.setRefBlockPrefix(this.refBlockPrefix);
        if(expiration == null) {
            expiration = LocalDateTime.now(ZoneOffset.UTC).plusSeconds(expiration_delay);
        }
        tr.setExpiration(expiration.withNano(0));
        
        return sign.signTransaction(tr, keys);
    }
    
    private void packOperations(Transaction tr) {
        var ops = this.operations
            .stream()
            .map(op -> new OperationPack(op))
            .collect(Collectors.toList());
        tr.setOperations(ops.toArray(new OperationPack[ops.size()]));
    }
    
    
}
