package bitwheeze.golos.goloslib.model.op;

import java.time.LocalDateTime;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountReferralOptions {
    private String referrer;
    private int interestRate;
    private LocalDateTime endDate;
    private Asset breakFee;
}
