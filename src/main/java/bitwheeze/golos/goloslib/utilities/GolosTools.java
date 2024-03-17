package bitwheeze.golos.goloslib.utilities;

import bitwheeze.golos.goloslib.model.*;
import bitwheeze.golos.goloslib.types.ChainTypes;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public static BigDecimal calcEmissionPerDay(BigDecimal vestingShares, DynamicGlobalProperties props) {
        var total = props.getTotalVestingShares().getValue();
        var acc = convertGolosToVestings(props.getAccumulativeEmissionPerDay().getValue(), props);
        return acc.multiply(vestingShares).divide(total, RoundingMode.HALF_DOWN).setScale(6, RoundingMode.DOWN);
    }

    public static BigDecimal convertVestingsToGolos(BigDecimal vestings, DynamicGlobalProperties props) {
        var totalVestings = props.getTotalVestingShares().getValue();
        var totalGolos = props.getTotalVestingFundSteem().getValue();
        return totalGolos.multiply(vestings).divide(totalVestings, RoundingMode.HALF_DOWN).setScale(3, RoundingMode.DOWN);
    }

    public static BigDecimal convertGolosToVestings(BigDecimal golos, DynamicGlobalProperties props) {
        var totalVestings = props.getTotalVestingShares().getValue();
        var totalGolos = props.getTotalVestingFundSteem().getValue();
        return totalVestings.multiply(golos).divide(totalGolos, RoundingMode.HALF_DOWN).setScale(6, RoundingMode.DOWN);
    }

}
