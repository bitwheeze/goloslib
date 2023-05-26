package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.utilities.GolosTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
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


    @SpringBootApplication
    static class TestConfiguration {
    }
}