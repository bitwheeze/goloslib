package bitwheeze.golos.goloslib.model.op;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonDeserialize(using = AccountCreateWithDelegationExtensionDeserializer.class) 
public class AccountCreateWithDelegationExtension {
    private AccountReferralOptions accountReferralOptions;
}
