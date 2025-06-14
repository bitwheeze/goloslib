package bitwheeze.golos.goloslib.model.exchange;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExRow {
    private Asset par;
    private Asset res;
}
