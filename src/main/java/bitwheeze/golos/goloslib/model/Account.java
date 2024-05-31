package bitwheeze.golos.goloslib.model;

import java.time.LocalDateTime;
import java.util.List;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.Authority;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Account {
    private long id;
    private String name;
    private Authority owner;
    private Authority active;
    private Authority posting;
    private String memoKey;
    private String jsonMetadata;
    private String proxy;
    private LocalDateTime lastOwnerUpdate;
    private LocalDateTime lastAccountUpdate;    
    private LocalDateTime created;    
    private boolean mined;
    private boolean ownerChallenged;
    private boolean activeChallenged;
    private LocalDateTime lastOwnerProved;    
    private LocalDateTime lastActiveProved;    
    private String recoveryAccount;    
    private LocalDateTime lastAccountRecovery;    
    private String resetAccount;
    private long commentCount;
    private long lifetimeVoteCount;
    private long postCount;
    private boolean canVote;
    private int votingPower;
    private LocalDateTime lastVoteTime;
    private Asset balance;
    private Asset savingsBalance;
    private Asset accumulativeBalance;
    private Asset tipBalance;
    private Asset sbdBalance;
    private long sbdSeconds;
    private LocalDateTime sbdSecondsLastUpdate;
    private LocalDateTime sbdLastInterestPayment;
    private Asset savingsSbdBalance;
    private long savingsSbdSeconds;
    private LocalDateTime savingsSbdSecondsLastUpdate;
    private LocalDateTime savingsSbdLastInterestPayment;
    private long savingsWithdrawRequests;
    private Asset vestingShares;
    private Asset delegatedVestingShares;
    private Asset receivedVestingShares;
    private Asset vestingWithdrawRate;
    private LocalDateTime nextVestingWithdrawal;
    private long withdrawn;
    private long toWithdraw;
    private long withdrawRoutes;
    private long benefactionRewards;
    private long curationRewards;
    private long delegationRewards;
    private long postingRewards;
    private List<Long> proxiedVsfVotes;
    private long witnessesVotedFor;
    private long averageBandwidth;
    private long averageMarketBandwidth;
    private long averageCustomJsonBandwidth;
    private long lifetimeBandwidth;
    private long lifetimeMarketBandwidth;
    private long lifetimeCustomJsonBandwidth;
    private LocalDateTime lastBandwidthUpdate;
    private LocalDateTime lastMarketBandwidthUpdate;
    private LocalDateTime lastCustomJsonBandwidthUpdate;
    private LocalDateTime lastComment;
    private LocalDateTime lastPost;
    private int postBandwidth;
    private List<String> witnessVotes;
    private long reputation;
    private long postsCapacity;
    private long commentsCapacity;
    private long votingCapacity;
    private long referralCount;
    private long sponsorCount;
    private String referrerAccount;
    private int referrerInterestRate;
    private LocalDateTime referralEndDate;
    private Asset referralBreakFee;
    private LocalDateTime lastActiveOperation;
    private LocalDateTime lastClaim;
    private LocalDateTime claimExpiration;
    private int provedHf;
    private boolean frozen;
    private AccountFreeze freeze;
    private boolean doNotBother;
    private Services services;
    private Asset marketBalance;
    private Asset marketSbdBalance;
    private Asset emissionDelegatedVestingShares;
    private Asset emissionReceivedVestingShares;
}

