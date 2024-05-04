package bitwheeze.golos.goloslib.model;

import bitwheeze.golos.goloslib.model.Asset;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class Content {
    long id;
    String author;
    String permlink;
    String hashlink;
    String parentAuthor;
    String parentPermlink;
    String category;
    String title;
    String body;
    String jsonMetadata;
    LocalDateTime lastUpdate;
    LocalDateTime created;
    LocalDateTime active;

    int numChanges;

    LocalDateTime lastPayout;
    int depth;
    int children;
    String childrenRshares2;
    long netRshares;
    long absRshares;
    long voteRshares;

    String childrenAbsRshares;

    LocalDateTime cashoutTime;
    LocalDateTime maxCashoutTime;

    String totalVoteWeight;

    int rewardWeight;
    Asset donates;
    int donatesUia;

    Asset totalPayoutValue;    //160.468 GBG",
    Asset beneficiaryPayoutValue;    //0.000 GBG",
    Asset beneficiaryGestsPayoutValue;    //0.000000 GESTS",
    Asset curatorPayoutValue;    //159.191 GBG",
    Asset curatorGestsPayoutValue;    //10890179.160139 GESTS",
    long authorRewards;    //3820675,
    Asset authorPayoutInGolos;    //3820.676 GOLOS",
    Asset authorGbgPayoutValue;    //0.000 GBG",
    Asset authorGolosPayoutValue;    //0.000 GOLOS",
    Asset authorGestsPayoutValue;    //10977529.897032 GESTS",
    long netVotes;    //58,
    String mode;    //archived",
    String curationRewardCurve;    //linear",
    String auctionWindowRewardDestination;    //to_curators",
    long auctionWindowSize;    //0,
    long auctionWindowWeight;    //0,
    long votesInAuctionWindowWeight;    //0,
    long rootComment;    //10152707,
    String rootTitle;    //Обновление игрушки",
    String rootAuthor;
    String app;
    Asset maxAcceptedPayout;    //10000.000 GBG",
    int percentSteemDollars;    //0,
    boolean allowReplies;    //true,
    boolean allowVotes;    //true,
    boolean allowCurationRewards;    //true,

    Asset decryptFee;

    int curationRewardsPercent;    //5000,
    Asset minGolosPowerToCurate;    //5000.000 GOLOS",
    boolean has_worker_request;    //false,
    //beneficiaries;    //[],
    String url;    ///ru--igry/@bitwheeze/obnovlenie-igrushki",
    Asset pendingAuthorPayoutValue;    //0.000 GBG",
    Asset pendingAuthorPayoutInGolos;    //0.000 GOLOS",
    Asset pendingAuthorPayoutGbgValue;    //0.000 GBG",
    Asset pendingAuthorPayoutGestsValue;    //0.000000 GESTS",
    Asset pendingAuthorPayoutGolosValue;    //0.000 GOLOS",
    Asset pendingBenefactorPayoutValue;    //0.000 GBG",
    Asset pendingBenefactorPayoutGestsValue;    //0.000000 GESTS",
    Asset pendingCuratorPayoutValue;    //0.000 GBG",
    Asset pendingCuratorPayoutGestsValue;    //0.000000 GESTS",
    Asset pendingPayoutValue;    //0.000 GBG",
    Asset totalPendingPayoutValue;    //0.000 GBG",
    //List<ContentVote> active_votes;    //[],
    int activeVotesCount;    //60,
    //List<String> replies;    //[],
    String authorReputation;    //804421096739506",
    Asset promoted;    //0.000 GBG",
    long bodyLength;    //0,
    //reblogged_by;    //[],
    //reblog_entries;    //[]
}
