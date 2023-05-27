package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.op.DonateMemo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootTest
@ComponentScan("bitwheeze.golos.goloslib")
class GoloslibApplicationTests {


    @Autowired
    ObjectMapper mapper;

    @Autowired
    TransactionFactory transactionFactory;

    @Test
    void serializeDonate() throws JsonProcessingException {
        var donateMemo = new DonateMemo("bw-solitaire", 3, "{}", "test");
        var donateMemoString = mapper.writeValueAsString(donateMemo);
        log.info("donateMemo '{}'", donateMemoString);
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
