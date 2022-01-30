package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.op.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@ComponentScan("bitwheeze.golos.goloslib")
class SecurityUtilsTest {
    private final String SEED = "test1";
    private final String PRIVKEY = "5J2K9a455SWKoqrHNpkovM438T4RtT2QDikSENK1xuzkFxv45LW";
    private final String PUBKEY = "GLS6WjKWZ8dHTfo1Y3YNt8EcMLHvgefYVv4xU6vu8omwNVnxYVenq";

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    TransactionFactory transactionBuilderFactory;

    @Autowired
    private GolosApi api;

    @Test
    public void getPublicKeyFromPrivateKey() {
        String pubKey = SecurityUtils.getPublicKeyFromPrivateKey(PRIVKEY);
        System.out.println("pubKey1 '" +  pubKey + "'");
        Assertions.assertEquals(PUBKEY, pubKey);
    }

    @Test
    public void getPrivateKeyFromSeed() {
        var privKey = SecurityUtils.getPrivateKeyFromString(SEED);
        var pubKey = SecurityUtils.getPublicKeyFromPrivateKey(privKey);
        Assertions.assertEquals(PUBKEY, pubKey);
    }

    private final static String TR="22c0cb81c08d9be67361020209626974776865657a6509626974776865657a65e80300000000000003474f4c4f5300000f54657374205472616e7366657220320209626974776865657a6509626974776865657a65d00700000000000003474f4c4f5300000f54657374205472616e73666572203200";

    @Test
    public void sign() {
        var builder = transactionBuilderFactory.getBuidler();
        Transfer transfer = new Transfer();
        transfer.setFrom("bitwheeze");
        transfer.setTo("bitwheeze");
        transfer.setAmount(new Asset(new BigDecimal("1.000"), "GOLOS"));
        transfer.setMemo("Test Transfer 2");

        var tr = builder
                .add(transfer)
                .expiration(LocalDateTime.of(2022, 01, 01, 00, 00, 00))
                .buildAndSign(new String [] {PRIVKEY});

        log.info("signatures {}", tr.getSignatures());
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}