package bitwheeze.golos.goloslib.model.exchange;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExChain {
    private Asset res;
    private List<ExchangeStep> steps;
    private List<String> syms;
    private List<ExChain> subchains;
    private List<ExRow> rows;
    private boolean hasRemain;
    private boolean reversed;
    private Asset empty;
    private ExchangeStep emptyStep;

    private List<String> logs;
    private String errReport;
}

