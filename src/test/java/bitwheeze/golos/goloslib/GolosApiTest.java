package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.VestingDelegation;
import bitwheeze.golos.goloslib.model.exception.BlockchainError;
import bitwheeze.golos.goloslib.model.nft.NftToken;
import bitwheeze.golos.goloslib.model.nft.NftTokenQuery;
import bitwheeze.golos.goloslib.model.op.NftCollection;
import bitwheeze.golos.goloslib.model.op.NftCollectionDelete;
import bitwheeze.golos.goloslib.model.op.NftIssue;
import bitwheeze.golos.goloslib.model.op.NftTransfer;
import bitwheeze.golos.goloslib.types.DelegationType;
import bitwheeze.golos.goloslib.utilities.GolosTools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ComponentScan("bitwheeze.golos.goloslib")
@RequiredArgsConstructor
class GolosApiTest {

    @Autowired private TransactionFactory transactionFactory;
    @Autowired private DatabaseApi api;
    @Autowired private SocialNetworkApi social;
    @Autowired private OperationHistoryApi history;
    @Autowired private WitnessApi witness;
    @Autowired private EventApi eventApi;
    @Autowired private NetworkBroadcastApi netApi;
    @Autowired private NftApi nftApi;

    private static final String POSTING_WIF = "5HwQScueMZdELZpjVBD4gm6xhiKiMqGx18g4WtQ6wVr4nBdSxY5";
    private static final String ACTIVE_WIF = "5K67PNheLkmxkgJ5UjvR8Nyt3GVPoLEN1dMZjFuNETzrNyMecPG";

    @Test
    void getDynamicGlobalProperties() {
        log.info(api.getDynamicGlobalProperties().block().orElseThrow().toString());
    }

    @Test
    void getBlock() {
        final long TEST_BLOCK_NUM = 78836541;
        final String TEST_PREV = "04b0983fa9fe9f7c9b8590daaa466ca2bca58238";
        var block = api.getBlock(TEST_BLOCK_NUM).block().orElseThrow();
        log.info("got response {}", block);
        log.info("comment_options {}", block.getTransactions()[0].getOperations()[1]);
        assertEquals(TEST_PREV, block.getPrevious());
    }

    @Test
    @SneakyThrows
    void getBlockRange() {
        long startBlock = 78682176;
        for(int i = 0; i < 50000; i++) {
            final var blockNo = startBlock + i;
            var block = api.getBlock(blockNo).block().orElseThrow();
            log.info("block {}: {}", blockNo, block.getTransactions());
            Thread.sleep(500);
        }
    }

    @Test
    void getAccount() {
        final String TEST_ACC = "bitwheeze";
        var acc = api.getAccount(TEST_ACC).block();
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.orElseThrow().getName());
    }

    @Test
    void getAccountRandom() {
        final String TEST_ACC = "random";
        var acc = api.getAccount(TEST_ACC);
        log.info("random account {}", acc);
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.block().orElseThrow().getName());
    }

    @Test
    void getAccountsStringArr() {
        final String TEST_ACC = "bitwheeze";
        var acc = api.getAccounts(new String[] {TEST_ACC}).block().orElseThrow().get(0);
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.getName());
    }

    @Test
    void getAccountsBalancesArr() {
        final String TEST_ACC = "prizm";
        var acc = api.getAccountsBalances(new String[] {TEST_ACC}).block().orElseThrow().get(0);
        assertNotNull(acc);
        assertNotNull(acc.get("YMPZM"));
        log.info("prizm balances {}", acc.get("YMPZM"));
    }

    @Test
    void getAccountsStringList() {
        final String TEST_ACC = "bitwheeze";
        var acc = api.getAccounts(List.of(TEST_ACC)).block().orElseThrow().get(0);
        assertNotNull(acc);
        assertEquals(TEST_ACC, acc.getName());
    }

    @Test
    void getOpsInBlock() {
        final long TEST_BLOCK_NUM = 52518042;
        var ops = history.getOpsInBlock(TEST_BLOCK_NUM, false).block().orElseThrow();
        //assertFalse(ops.isEmpty());
    }

    @Test
    void getAssets() {
        var assets = api.getAssets("", new String [] {"PRIZM"}, "", 2, null).block().orElseThrow();
        assertEquals(1, assets.size());
        assertEquals("6000000000.00 PRIZM", assets.get(0).getMaxSupply());
        assertEquals(2, assets.get(0).getPrecision());
    }

    @Test
    void broadcastTransaction() {
    }

    @Test
    void lookupAccounts() {
        var accs = api.lookupAccounts("bitwhe", 1).block().orElseThrow();
        assertFalse(accs.isEmpty());
    }

    @Test
    void getTransactionHex() {
        var tr = transactionFactory
                .getBuidler()
                .addTransfer("bitwheeze", "ebgerghberbuebreurzgb", "1.000", "GOLOS", "test")
                .setReferenceBlock()
                .build();
        var hexed = api.getTransactionHex(tr).block().orElseThrow();
    }

    @Test
    void badTransaction() {
        var tr = transactionFactory
                .getBuidler()
                .addTransfer("bitwheeze", "ebgerghberbuebreurzgb", "1.000", "GOLOS", "test")
                .setReferenceBlock()
                .build();

        try {
            var response = netApi.broadcastTransaction(tr).block().orElseThrow();
        } catch (BlockchainError error) {
            log.info("error = {}", error.getError());
            return;
        }
        fail("Expected an exception");

    }


    @Test
    void createNftCollection() throws JsonProcessingException {
        @Data
        class CollectionDesc {
            String desc = "Medal of Honor";
            String app = "lexicon";
        }

        //{"hero":"Canon the Barbarian","desc":"Рубит всех своим мечем налево и на право","type":"legendary","health":120,"str":100,"defence":40,"mana":10,"consumes":"red,blue"}
        @Data
        class TokenDesc {
            String id = UUID.randomUUID().toString();
            String title = "Canon the Barbarian";
            String description = "Рубит всех своим мечем налево и на право";
            String image = "https://devimages.golos.today/0x0/https://steemitimages.com/0x0/https://i.imgur.com/V1pnpH8.png";
            String type = "legendary";
            int health = 120;
            int strength = 100;
            int defence = 40;
            int mana = 10;
            String [] consumes = new String[] {"red", "blue"};
        }

        @Data
        class Medal {
            String id = UUID.randomUUID().toString();
            String title = "Speedy Gonzales";
            String description = "Значок отличника раскладывания пасьянса. Самый быстрый расклад. Владелец этого значка может разово вернуть сделанную ставку.";
            String image = "https://devimages.golos.today/0x0/https://steemitimages.com/0x0/https://i.imgur.com/1RzAWCi.jpg";
            String type = "legendary";
        }

        var tr = transactionFactory
                .getBuidler()
                .add(new NftCollection("travian", "GOLOS.BACKERS", new ObjectMapper().writeValueAsString(new CollectionDesc()), 1000, new String[0]))
                .buildAndSign(new String[] {ACTIVE_WIF});
        netApi.broadcastTransaction(tr).block().orElseThrow();

           var tr1 = transactionFactory
                    .getBuidler()
                    .add(new NftIssue("travian", "GOLOS.BACKERS", "travian", new ObjectMapper().writeValueAsString(new TokenDesc()), new String[0]))
                    .add(new NftIssue("travian", "GOLOS.BACKERS", "travian", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "travian", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "travian", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "travian", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                  .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                  .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                  .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                  .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                  .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                  .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "lex", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "travian", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                   .add(new NftIssue("travian", "GOLOS.BACKERS", "travian", new ObjectMapper().writeValueAsString(new Medal()), new String[0]))
                    .buildAndSign(new String[]{ACTIVE_WIF});
            netApi.broadcastTransaction(tr1).block().orElseThrow();


            List<NftToken> tokens = nftApi.getNftToken(NftTokenQuery.builder().owner("lex").limit(100).build()).block().orElseThrow();
            log.info("tokens size {}", tokens.size());
            tokens.forEach(t -> log.info("token {}", t));
        {
            tr = transactionFactory
                    .getBuidler()
                    .add(new NftCollectionDelete("lex", "GOLOS.BACKER", new String[0]))
                    .buildAndSign(new String[] {ACTIVE_WIF});
            netApi.broadcastTransaction(tr).block().orElseThrow();
        }

    }

    @Test
    void getContent() {
        var content = social.getContent("bitwheeze", "volya", 0, 0).block().orElseThrow();
        log.info("content {}", content);
        assertNotNull(content);
        assertTrue(content.getId() > 0);
        assertEquals("bitwheeze", content.getAuthor());
        assertEquals("volya", content.getPermlink());

    }

    @Test
    void getContentNotExisting() {
        var content = social.getContent("bitwheeze", "volyag63g3746g736g4r374", 0, 0).block().orElseThrow();
        log.info("content {}", content);
        assertNotNull(content);
        assertEquals(0, content.getId());
        assertEquals("", content.getAuthor());
        assertEquals("", content.getPermlink());
    }

    @Test
    void getChainProperties() {
        var props = api.getChainProperties().block().orElseThrow();
        log.info("props = {}", props);
        assertTrue(props.getMaximumBlockSize() > 0);
    }

    @Test
    void getConfig() {
        var config = api.getConfig().block().orElseThrow();
        log.info("config = {}", config);
        assertTrue(config.getSteemitVoteRegenerationSeconds() > 0);
    }

    @Test void getCurrentVotePower() {
        var config = api.getConfig().block().orElseThrow();
        var bitwheeze = api.getAccount("bitwheeze").block();
        assertTrue(bitwheeze.isPresent());
        int current_vote_power = GolosTools.calcCurrentVotePower(bitwheeze.get(), config);

        long secondsFullRegeneration = (long)GolosTools.calcSecondsTillFullRegenerated(bitwheeze.get(), config);

        log.info("current_vote_power = {}, remaining seconds = {}", current_vote_power, LocalDateTime.now().plusSeconds(secondsFullRegeneration));
    }

    @Test void getCurrentMedianHistoryPrice() {
        var price = witness.getGetCurrentMedianHistoryPrice().block().orElseThrow();
        log.info("current price {}", price );
    }

    @Test void getEventsInBlock() {
        var eventList = eventApi.getEventsInBlock(70239546, false).block().orElseThrow();
        eventList.stream().forEach( event -> log.info("  event {}", event));

        eventList = eventApi.getEventsInBlock(70781322, false).block().orElseThrow();
        eventList.stream().forEach( event -> log.info("  event {}", event));

        eventList = eventApi.getEventsInBlock(70784913, false).block().orElseThrow();
        eventList.stream().forEach( event -> log.info("  event {}", event));

        eventList = eventApi.getEventsInBlock(70653402, false).block().orElseThrow();
        eventList.stream().forEach( event -> log.info("  event {}", event));

        eventList = eventApi.getEventsInBlock(70784465, false).block().orElseThrow();
        eventList.stream().forEach( event -> log.info("  event {}", event));



    }

    @Test void getEmission() {
        var props = api.getDynamicGlobalProperties().block().orElseThrow();
        log.info("conversion rate {}", GolosTools.convertGolosToVestings(BigDecimal.ONE, props));
        log.info("conversion rate {}", GolosTools.convertVestingsToGolos(GolosTools.convertGolosToVestings(BigDecimal.ONE, props), props));
        var delegatedVestings = api.getVestingDelegations("bitwheeze", "ramin", 100, DelegationType.received).block().orElseThrow();
        var received = delegatedVestings.stream()
                .peek(d -> log.info("received delelgation {}", d))
                .filter(VestingDelegation::isEmission)
                .map(d -> d.getVestingShares().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        log.info("receved with emission {}", received);
        log.info("emission per day total = {}", props.getAccumulativeEmissionPerDay());
        if(received.compareTo(BigDecimal.ZERO) > 0) {
            var emission = GolosTools.calcEmissionPerDay(received, props);
            log.info("Emission per day from Prizm = {}", GolosTools.convertVestingsToGolos(emission, props));
            
        }
    }

    @SpringBootApplication
    static class TestConfiguration {
    }

}