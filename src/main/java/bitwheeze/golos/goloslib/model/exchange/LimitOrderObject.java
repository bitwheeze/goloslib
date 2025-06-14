package bitwheeze.golos.goloslib.model.exchange;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitOrderObject {
    private long id; // Placeholder for id_type

    private LocalDateTime created; // Using LocalDateTime for time_point_sec
    private LocalDateTime expiration; // Using LocalDateTime for time_point_sec
    private String seller; // Placeholder for account_name_type
    private long orderid = 0; // Using long for uint32_t
    private boolean forSale; // Placeholder for share_type
    private Asset symbol; // Placeholder for asset_symbol_type
    private Price sellPrice; // Placeholder for price
}
