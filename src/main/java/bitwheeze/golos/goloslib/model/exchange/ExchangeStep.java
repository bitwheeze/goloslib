package bitwheeze.golos.goloslib.model.exchange;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeStep {
    private Asset sell; // Initialized by constructor if needed
    private Asset receive; // Initialized by constructor if needed
    private Asset remain;
    private Asset fee;
    private boolean isBuy = false;
    private Short feePct;

    private Price bestPrice; // Initialized by constructor if needed
    private Price limitPrice; // Initialized by constructor if needed

    private List<LimitOrderObject> impactedOrders;
}
