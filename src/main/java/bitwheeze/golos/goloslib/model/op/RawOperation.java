package bitwheeze.golos.goloslib.model.op;

import lombok.Data;
import lombok.ToString;

/**
 * String representation of an unknown operation.
 * @author bitwheeze
 *
 */
@Data
@ToString
public class RawOperation extends Operation {
    private String opName;
    private String opBody;
}
