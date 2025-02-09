package bitwheeze.golos.goloslib.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OrderBook {
    List<OrderBookPair> bids;
    List<OrderBookPair> asks;
}
