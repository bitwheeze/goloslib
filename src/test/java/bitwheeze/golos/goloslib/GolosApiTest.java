package bitwheeze.golos.goloslib;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan("bitwheeze.golos.goloslib")
class GolosApiTest {

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    TransactionFactory transactionFactory;

    @Autowired
    private GolosApi api;

    @Test
    void getDynamicGlobalProperties() {
    }

    @Test
    void getBlock() {
        final long TEST_BLOCK_NUM = 52518042;
        final String TEST_PREV = "03215c996c7c4ad648b94169aeb639e28db1b4bb";
        var block = api.getBlock(TEST_BLOCK_NUM).orElseThrow();
        assertEquals(TEST_PREV, block.getPrevious());
    }

    @Test
    void getAccount() {
        final String TEST_ACC = "bitwheeze";
        var acc = api.getAccount(TEST_ACC);
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.orElseThrow().getName());
    }

    @Test
    void getAccountsStringArr() {
        final String TEST_ACC = "bitwheeze";
        var acc = api.getAccounts(new String[] {TEST_ACC}).orElseThrow().get(0);
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.getName());
    }

    @Test
    void getAccountsStringList() {
        final String TEST_ACC = "bitwheeze";
        var acc = api.getAccounts(List.of(TEST_ACC)).orElseThrow().get(0);
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.getName());
    }

    @Test
    void getOpsInBlock() {
        final long TEST_BLOCK_NUM = 52518042;
        var ops = api.getOpsInBlock(TEST_BLOCK_NUM, false).orElseThrow();
        assertFalse(ops.isEmpty());
    }

    @Test
    void broadcastTransaction() {
    }

    @Test
    void lookupAccounts() {
        var accs = api.lookupAccounts("bitwhe", 1).orElseThrow();
        assertFalse(accs.isEmpty());
    }

    @Test
    void getTransactionHex() {
        var tr = transactionFactory
                .getBuidler()
                .addTransfer("bitwheeze", "bitwheeze", "1.000", "GOLOS", "test")
                .setReferenceBlock()
                .build();
        var hexed = api.getTransactionHex(tr).orElseThrow();
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}