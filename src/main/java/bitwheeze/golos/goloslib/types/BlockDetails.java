package bitwheeze.golos.goloslib.types;

import bitwheeze.golos.goloslib.model.Block;

public record BlockDetails(
        long blockNum,
        Block block
) {
}
