package bitwheeze.golos.goloslib.model.exception;

import lombok.Getter;

public class BlockchainError extends RuntimeException {

    @Getter
    NodeError error;
    public BlockchainError(NodeError error) {
        super(error != null?error.getMessage():"Unknown error in response!");
        this.error = error;
    }
}
