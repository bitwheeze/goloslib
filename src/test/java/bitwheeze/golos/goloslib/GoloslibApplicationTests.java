package bitwheeze.golos.goloslib;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("bitwheeze.golos.goloslib")
class GoloslibApplicationTests {

    @Test
    void contextLoads() {
        
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
