package bitwheeze.golos.goloslib.utilities;

import bitwheeze.golos.goloslib.model.Account;
import bitwheeze.golos.goloslib.model.ChainProperties;
import bitwheeze.golos.goloslib.model.Config;
import bitwheeze.golos.goloslib.types.ChainTypes;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class GolosTools {

    public static int calcCurrentVotePower(Account account, Config config) {
        return calcCurrentVotePower(account, config, LocalDateTime.now(ZoneOffset.UTC));
    }


    public static int calcCurrentVotePower(Account account, Config config, LocalDateTime now) {
        var elapsed_seconds = now.toEpochSecond(ZoneOffset.UTC) - account.getLastVoteTime().toEpochSecond(ZoneOffset.UTC);

        long regenerated_power =
                (config.getSteemit100Percent() * elapsed_seconds) /
                        config.getSteemitVoteRegenerationSeconds();

        return (int)Math.min(
                account.getVotingPower() + regenerated_power,
                config.getSteemit100Percent());
    }

    public static long calcSecondsTillFullRegenerated(Account account, Config config) {
        var current_power = calcCurrentVotePower(account, config);
        return config.getSteemitVoteRegenerationSeconds() * (long)(config.getSteemit100Percent() - current_power) / config.getSteemit100Percent();
    }
}
