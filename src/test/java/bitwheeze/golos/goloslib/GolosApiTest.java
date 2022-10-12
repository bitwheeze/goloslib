package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.utilities.GolosTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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
        var block = api.getBlock(TEST_BLOCK_NUM);
        log.info("got response {}", block);
        assertEquals(TEST_BLOCK_NUM+"", block.getId());
        assertEquals(TEST_PREV, block.orElseThrow().getPrevious());
    }

    @Test
    void getAccount() {
        final String TEST_ACC = "bitwheeze";
        var acc = api.getAccount(TEST_ACC);
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.orElseThrow().getName());
    }

    @Test
    void getAccountRandom() {
        final String TEST_ACC = "random";
        var acc = api.getAccount(TEST_ACC);
        log.info("random account {}", acc);
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
    void getAccountsBalancesArr() {
        final String TEST_ACC = "prizm";
        var acc = api.getAccountsBalances(new String[] {TEST_ACC}).orElseThrow().get(0);
        assertNotNull(acc);
        assertNotNull(acc.get("YMPZM"));
        log.info("prizm balances {}", acc.get("YMPZM"));
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
    void getAssets() {
        var assets = api.getAssets("", new String [] {"PRIZM"}, "", 2, null).orElseThrow();
        assertEquals(1, assets.size());
        assertEquals("6000000000.00 PRIZM", assets.get(0).getMaxSupply());
        assertEquals(2, assets.get(0).getPrecision());
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

    @Test
    void getContent() {
        var content = api.getContent("bitwheeze", "volya", 0, 0).orElseThrow();
        log.info("content {}", content);
        assertNotNull(content);
        assertTrue(content.getId() > 0);
        assertEquals("bitwheeze", content.getAuthor());
        assertEquals("volya", content.getPermlink());

    }

    @Test
    void getContentNotExisting() {
        var content = api.getContent("bitwheeze", "volyag63g3746g736g4r374", 0, 0).orElseThrow();
        log.info("content {}", content);
        assertNotNull(content);
        assertEquals(0, content.getId());
        assertEquals("", content.getAuthor());
        assertEquals("", content.getPermlink());
    }

    @Test
    void getChainProperties() {
        var props = api.getChainProperties().orElseThrow();
        log.info("props = {}", props);
        assertTrue(props.getMaximumBlockSize() > 0);
    }

    @Test
    void getConfig() {
        var config = api.getConfig().orElseThrow();
        log.info("config = {}", config);
        assertTrue(config.getSteemitVoteRegenerationSeconds() > 0);
    }

    @Test void getCurrentVotePower() {
        var config = api.getConfig().orElseThrow();
        var bitwheeze = api.getAccount("bitwheeze");
        assertTrue(bitwheeze.isPresent());
        int current_vote_power = GolosTools.calcCurrentVotePower(bitwheeze.get(), config);

        long secondsFullRegeneration = (long)GolosTools.calcSecondsTillFullRegenerated(bitwheeze.get(), config);

        log.info("current_vote_power = {}, remaining seconds = {}", current_vote_power, LocalDateTime.now().plusSeconds(secondsFullRegeneration));
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}