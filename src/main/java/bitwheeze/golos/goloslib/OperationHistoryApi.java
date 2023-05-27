package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.ApiResponse;
import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.model.api.DatabaseMethods;
import bitwheeze.golos.goloslib.model.api.OperationHistoryMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class OperationHistoryApi extends GolosApiReactive {


    public OperationHistoryApi(@Qualifier("golos_api") WebClient client) {
        super(client);
    }


    public Mono<ApiResponse.OpsInBlockResponse> getOpsInBlock(long blockNum, boolean onlyVirtual) {
        var method = OperationHistoryMethods.getOpsInBlock();
        method.getMethodParams()[0] = blockNum;
        method.getMethodParams()[1] = onlyVirtual;
        return send(method, ApiResponse.OpsInBlockResponse.class);
    }


}
