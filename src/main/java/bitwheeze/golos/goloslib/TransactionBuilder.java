package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.Block;
import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.model.op.*;
import bitwheeze.golos.goloslib.types.BlockDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class TransactionBuilder {
    

    private final DatabaseApi api;
    private final SecurityUtils securityUtils;

    @Value("${golos.tr.expiration_delay:300}")
    private long expiration_delay;
    private long refBlockNum = 0;
    private long refBlockPrefix = 0;
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

    /**
     * Automatically read a last block and get their precessor
     * @return
     */
    public TransactionBuilder setReferenceBlock() {
        var refBlockNum = api.getDynamicGlobalProperties().block().orElseThrow().getHeadBlockNumber() - 3;
        setReferenceBlock(refBlockNum);
        return this;
    }

    /**
     * Set RefBlockNum & RefBlockPrefix
     * @return
     */
    public TransactionBuilder setReferenceBlock(long refBlockNum) {
        var refBlock = api.getBlock(refBlockNum + 1).block().orElseThrow();
        return setReferenceBlock(refBlockNum, refBlock);
    }

    public TransactionBuilder setReferenceBlock(long refBlockNum, Block refBlock) {
        this.refBlockNum = refBlockNum & 0xffff;
        var bytes = Hex.decode(refBlock.getPrevious());
        log.info("Previous block ( sync): {}, {}", refBlockNum, refBlock.getPrevious());
        this.refBlockPrefix = ByteBuffer.wrap(bytes, 4 ,4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        return this;
    }

    public Transaction setReferenceBlock(long refBlockNum, Block refBlock, Transaction tr) {
        final var maskedRefBlockNum = refBlockNum & 0xffff;
        var bytes = Hex.decode(refBlock.getPrevious());
        log.info("Previous block (async): {}, {}", refBlockNum, refBlock.getPrevious());
        final long refBlockPrefix = ByteBuffer.wrap(bytes, 4 ,4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        tr.setRefBlockNum(maskedRefBlockNum);
        tr.setRefBlockPrefix(refBlockPrefix);
        return tr;
    }

    public Transaction buildAndSign(String [] keys) {
        var tr = build();
        String serialized = api.getTransactionHex(tr).block().orElseThrow();
        return securityUtils.signTransaction(tr, serialized, keys);
    }

    public Mono<Transaction> buildAndSignAsync(String [] keys) {
        var monoBlockNum = Mono.just(refBlockNum);

        if(refBlockNum == 0) {
            monoBlockNum = api.getDynamicGlobalProperties()
                .mapNotNull(ApiResponse::orElseThrow)
                .map(props -> props.getHeadBlockNumber() -3L);
        }

        return monoBlockNum
                .flatMap(blockNum ->
                        api.getBlock(blockNum + 1).map(ApiResponse::orElseThrow).map(block -> new BlockDetails(blockNum, block))
                )
                .doOnNext(block -> log.info("Reference block (async): {}, {}", block.blockNum(), block.block().getPrevious()))
                .map(block -> {
                    Transaction tr = new Transaction();
                    packOperations(tr);
                    setReferenceBlock(block.blockNum(), block.block(), tr);
                    setExpiration(tr);
                    return tr;
                })
                .flatMap(tr ->
                    api.getTransactionHex(tr).map(ApiResponse::orElseThrow).map(serialized ->
                        securityUtils.signTransaction(tr, serialized, keys)
                    )
                );
    }
    
    private void packOperations(Transaction tr) {
        var ops = this.operations
            .stream()
            .map(op -> new OperationPack(op))
            .collect(Collectors.toList());
        tr.setOperations(ops.toArray(new OperationPack[ops.size()]));
    }

    public Transaction build() {

        if(operations.isEmpty()) {
            throw new RuntimeException("No operations added to transaction!");
        }
        Transaction tr = new Transaction();

        packOperations(tr);

        if(refBlockPrefix == 0) {
            this.setReferenceBlock();
        }

        tr.setRefBlockNum(this.refBlockNum);
        tr.setRefBlockPrefix(this.refBlockPrefix);
        if(expiration == null) {
            expiration = LocalDateTime.now(ZoneOffset.UTC).plusSeconds(expiration_delay);
        }
        tr.setExpiration(expiration.withNano(0));
        return tr;
    }

    public Transaction setExpiration(Transaction tr) {
        if(expiration == null) {
            expiration = LocalDateTime.now(ZoneOffset.UTC).plusSeconds(expiration_delay);
        }
        tr.setExpiration(expiration.withNano(0));
        return tr;
    }

    public TransactionBuilder addTransfer(String from, String to, String amount, String asset, String memo) {
        var transfer = new Transfer(from, to, new Asset(new BigDecimal(amount), asset), memo);
        return this.add(transfer);
    }

    public TransactionBuilder addDonate(String from, String to, String amount, String asset, DonateMemo memo) {
        var donate = new Donate(from, to, new Asset(new BigDecimal(amount), asset), memo, new String [0]);
        return this.add(donate);
    }

    public TransactionBuilder addVote(String voter, String author, String permlink, int weight) {
        var vote = new Vote();
        vote.setVoter(voter);
        vote.setAuthor(author);
        vote.setPermlink(permlink);
        vote.setWeight(weight);
        return this.add(vote);
    }
}
