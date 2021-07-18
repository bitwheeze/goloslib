package bitwheeze.golos.goloslib.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExchangeRate {
    private Asset base;
    private Asset quote;
}
