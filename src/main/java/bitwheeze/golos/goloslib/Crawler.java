package bitwheeze.golos.goloslib;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitwheeze.golos.goloslib.model.ApiResponse.DynamicGlobalPropertiesResponse;
import bitwheeze.golos.goloslib.model.ApiResponse.OpsInBlockResponse;
import bitwheeze.golos.goloslib.model.OperationHistoryRecord;
import bitwheeze.golos.goloslib.model.op.virtual.ProducerReward;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Crawler {
    @Autowired
    GolosApi api;
    
    long lastSeenBlock = -1;
    
    @PostConstruct
    public void init() {
        DynamicGlobalPropertiesResponse props = api.getDynamicGlobalProperties();
        lastSeenBlock = 49708010;//props.getResult().getLastIrreversibleBlockNum();
    }
    
    //@Scheduled(fixedRate = 3000)
    public void scan() {
        DynamicGlobalPropertiesResponse props = api.getDynamicGlobalProperties();
        long lastBlock = props.getResult().getLastIrreversibleBlockNum();
        for(;lastSeenBlock <= lastBlock; lastSeenBlock++) {
            OpsInBlockResponse opsInBlock = api.getOpsInBlock(lastSeenBlock, false);
            if(opsInBlock.getResult().size() > 1) {
                log.info("opsInBlock {} ", lastSeenBlock);
                for(OperationHistoryRecord rec : opsInBlock.getResult()) {
                    if(!(rec.getOp() instanceof ProducerReward)) {
                        log.info("   op = {}", rec.getOp());
                    }
                }
            }
        }
    }
}
