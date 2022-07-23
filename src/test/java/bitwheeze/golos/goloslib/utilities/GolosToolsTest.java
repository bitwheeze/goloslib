package bitwheeze.golos.goloslib.utilities;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.Config;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GolosToolsTest {

    @Test
    void calcCurrentVotePower() {
        var config = new Config();
        config.setSteemitVoteRegenerationSeconds(432000);
        config.setSteemit100Percent(10000);
        config.setSteemit1Percent(100);

        var now = LocalDateTime.now();

        var acc = new Account();
        acc.setVotingPower(0);
        acc.setLastVoteTime(now.minusSeconds(config.getSteemitVoteRegenerationSeconds()));

        assertEquals(10000, GolosTools.calcCurrentVotePower(acc, config, now));

        acc.setLastVoteTime(now.minusSeconds(config.getSteemitVoteRegenerationSeconds() / 2));
        assertEquals(5000, GolosTools.calcCurrentVotePower(acc, config, now));
    }
}