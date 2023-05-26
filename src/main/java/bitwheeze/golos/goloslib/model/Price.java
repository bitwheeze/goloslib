package bitwheeze.golos.goloslib.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Price {
    Asset base;
    Asset quote;
}
