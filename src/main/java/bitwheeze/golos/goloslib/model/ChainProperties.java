package bitwheeze.golos.goloslib.model;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChainProperties {
    Asset accountCreationFee; //25.000 GOLOS",
    int maximumBlockSize; //65536,
    int sbdInterestRate; //0,
    Asset createAccountMinGolosFee; //1.000 GOLOS",
    Asset createAccountMinDelegation; //25.000 GOLOS",
    int createAccountDelegationTime; //2592000,
    Asset minDelegation; //10.000 GOLOS",
    int maxReferralInterestRate; //1000,
    int maxReferralTermSec; //31104000,
    Asset minReferralBreakFee; //100.000 GOLOS",
    Asset maxReferralBreakFee; //10000.000 GOLOS",
    int postsWindow; //546,
    int postsPerWindow; //4,
    int commentsWindow; //546,
    int commentsPerWindow; //40,
    int votesWindow; //546,
    int votesPerWindow; //80,
    int auctionWindowSize; //0,
    int maxDelegatedVestingInterestRate; //9000,
    int customOpsBandwidthMultiplier; //10,
    int minCurationPercent; //5000,
    int maxCurationPercent; //5000,
    String curationRewardCurve; //linear",
    boolean allowDistributeAuctionReward; //true,
    boolean allowReturnAuctionRewardToFund; //true,
    int workerRewardPercent; //0,
    int witnessRewardPercent; //0,
    int vestingRewardPercent; //0,
    int workerEmissionPercent; //500,
    int vestingOfRemainPercent; //8750,
    Asset workerRequestCreationFee; //20.000 GBG",
    int workerRequestApproveMinPercent; //1500,
    int sbdDebtConvertRate; //100,
    int voteRegenerationPerDay; //10,
    int witnessSkippingResetTime; //21600,
    int witnessIdlenessTime; //2592000,
    int accountIdlenessTime; //15552000,
    int claimIdlenessTime; //604800,
    Asset minInviteBalance; //25.000 GOLOS",
    Asset assetCreationFee; //500.000 GBG",
    int inviteTransferIntervalSec; //60,
    int convertFeePercent; //500,
    Asset minGolosPowerToCurate; //5000.000 GOLOS",
    int negrepPostingWindow; //1440,
    int negrepPostingPerWindow; //1
}
