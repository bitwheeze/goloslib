package bitwheeze.golos.goloslib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class VestingDelegation {
    int id; //: 195170,
    String delegator;// ": "prizm",
    String delegatee;// ": "payme",
    Asset vestingShares; //": "8619571271.824690 GESTS",
    int interestRate; //": 10000,
    LocalDateTime minDelegationTime; //": "2024-02-25T13:00:09",
    @JsonProperty("is_emission")
    boolean isEmission; //": true
}
