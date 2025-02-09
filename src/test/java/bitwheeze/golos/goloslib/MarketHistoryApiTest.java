package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.op.LimitOrderCancel;
import bitwheeze.golos.goloslib.model.op.Operation;
import bitwheeze.golos.goloslib.model.op.Vote;
import bitwheeze.golos.goloslib.utilities.GolosTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ComponentScan("bitwheeze.golos.goloslib")
class MarketHistoryApiTest {

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    TransactionFactory transactionFactory;

    @Autowired
    private MarketHistoryApi api;

    @Autowired
    private NetworkBroadcastApi netApi;

    @Test
    void getOpenOrders() {
        var resp = api.getOpenOrders("lex", "YMHIVE", "GOLOS").block();
        {
            log.info("got order list! error = {}", resp.getError());
            if(!resp.isError() && resp.getResult().isPresent()) {
                resp.getResult().get().forEach(order -> {
                    log.info("order {} -> {}", order.getOrderid(), order);
                });
            }

        };
    }

    @Test
    public void getOrderBook() {
        var resp = api.getOrderBook("GOLOS", "GBG", 4).block();
        {
            log.info("got order book! error = {}", resp.getError());
            if(!resp.isError() && resp.getResult().isPresent()) {
                log.info("OrderBook: {}", resp.getResult().get().toString());
            }

        };
    }



    @Test
    public void closeAllOpenOrders() {
        final var builder = transactionFactory.getBuidler();
        List<Operation> ops = new ArrayList<>();
        api.getOpenOrders("lex", "YMHIVE", "GOLOS")
                .block()
                .orElseThrow()
                .stream()
                .map(openOrder -> {
                    log.info("open order {}", openOrder);
                    var cancelOp = new LimitOrderCancel();
                    cancelOp.setOwner(openOrder.getSeller());
                    cancelOp.setOrderid(openOrder.getOrderid());
                    return cancelOp;
                })
                .forEach(op -> ops.add(op));

        if(ops.isEmpty()) {
            return;
        }
        ops.forEach(op -> {
            log.info("add cancel order {}", op);
            builder.add(op);
        });
        var tr = builder.buildAndSign(new String [] {"5K67PNheLkmxkgJ5UjvR8Nyt3GVPoLEN1dMZjFuNETzrNyMecPG"});
        netApi.broadcastTransaction(tr).block().orElseThrow();

    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}