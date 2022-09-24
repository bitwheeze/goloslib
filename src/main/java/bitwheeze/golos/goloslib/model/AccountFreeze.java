package bitwheeze.golos.goloslib.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class AccountFreeze {
    private Authority owner;
    private Authority active;
    private Authority posting;
    private int hardfork;
    private LocalDateTime frozen;
}
