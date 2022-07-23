package bitwheeze.golos.goloslib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Config {
    @JsonProperty("STEEMIT_BUILD_TESTNET")
    boolean steemitBuildTestnet; //false,
    @JsonProperty("GRAPHENE_CURRENT_DB_VERSION")
    String grapheneCurrentDbVersion; //GPH2.4",
    @JsonProperty("SBD_SYMBOL")
    String sbdSymbol; //1195525891,
    @JsonProperty("STEEMIT_100_PERCENT")
    int steemit100Percent; //10000,
    @JsonProperty("STEEMIT_1_PERCENT")
    int steemit1Percent; //100,
    @JsonProperty("STEEMIT_ADDRESS_PREFIX")
    String steemitAddressPrefix; //GLS",
    @JsonProperty("STEEMIT_APR_PERCENT_MULTIPLY_PER_BLOCK")
    String steemitAprPercentMultiplyPerBlock; //102035135585887",
    @JsonProperty("STEEMIT_APR_PERCENT_MULTIPLY_PER_HOUR")
    String steemitAprPercentMultiplyPerHour; //119577151364285",
    @JsonProperty("STEEMIT_APR_PERCENT_MULTIPLY_PER_ROUND")
    String steemitAprPercentMultiplyPerRound; //133921203762304",
    @JsonProperty("STEEMIT_APR_PERCENT_SHIFT_PER_BLOCK")
    int steemitAprPercentShiftPerBlock; //87,
    @JsonProperty("STEEMIT_APR_PERCENT_SHIFT_PER_HOUR")
    int steemitAprPercentShiftPerHour; //77,
    @JsonProperty("STEEMIT_APR_PERCENT_SHIFT_PER_ROUND")
    int steemitAprPercentShiftPerRound; //83,
    @JsonProperty("STEEMIT_BANDWIDTH_AVERAGE_WINDOW_SECONDS")
    int steemitBandwidthAverageWindowSeconds; //604800,
    @JsonProperty("STEEMIT_BANDWIDTH_PRECISION")
    int steemitBandwidthPrecision; //1000000,
    @JsonProperty("STEEMIT_BLOCKCHAIN_PRECISION")
    int steemitBlockchainPrecision; //1000,
    @JsonProperty("STEEMIT_BLOCKCHAIN_PRECISION_DIGITS")
    int steemitBlockchainPrecisionDigits; //3,
    @JsonProperty("STEEMIT_BLOCKCHAIN_HARDFORK_VERSION")
    String steemitBlockchainHardforkVersion; //0.26.0",
    @JsonProperty("STEEMIT_BLOCKCHAIN_VERSION")
    String steemitBlockchainVersion; //0.26.0",
    @JsonProperty("STEEMIT_BLOCK_INTERVAL")
    int steemitBlockInterval; //3,
    @JsonProperty("STEEMIT_BLOCKS_PER_DAY")
    int steemitBlocksPerDay; //28800,
    @JsonProperty("STEEMIT_BLOCKS_PER_HOUR")
    int steemitBlocksPerHour; //1200,
    @JsonProperty("STEEMIT_BLOCKS_PER_YEAR")
    int steemitBlocksPerYear; //10512000,
    @JsonProperty("STEEMIT_CASHOUT_WINDOW_SECONDS")
    int steemitCashoutWindowSeconds; //604800,
    @JsonProperty("STEEMIT_CHAIN_ID")
    String steemitChainId; //782a3039b478c839e4cb0c941ff4eaeb7df40bdd68bd441afd444b9da763de12",
    @JsonProperty("STEEMIT_CONTENT_APR_PERCENT")
    int steemitContentAprPercent; //5813,
    @JsonProperty("STEEMIT_CONVERSION_DELAY")
    String steemitConversionDelay; //302400000000",
    @JsonProperty("STEEMIT_CURATE_APR_PERCENT")
    int steemitCurateAprPercent; //1937,
    @JsonProperty("STEEMIT_DEFAULT_SBD_INTEREST_RATE")
    int steemitDefaultSbdInterestRate; //1000,
    @JsonProperty("STEEMIT_FEED_HISTORY_WINDOW")
    int steemitFeedHistoryWindow; //84,
    @JsonProperty("STEEMIT_FEED_INTERVAL_BLOCKS")
    int steemitFeedIntervalBlocks; //1200,
    @JsonProperty("STEEMIT_FREE_TRANSACTIONS_WITH_NEW_ACCOUNT")
    int steemitFreeTransactionsWithNewAccount; //100,
    @JsonProperty("STEEMIT_GENESIS_TIME")
    LocalDateTime steemitGenesisTime; //2016-10-18T11:00:00",
    @JsonProperty("STEEMIT_HARDFORK_REQUIRED_WITNESSES")
    int steemitHardforkRequiredWitnesses; //17,
    @JsonProperty("STEEMIT_INIT_MINER_NAME")
    String steemitInitMinerName; //cyberfounder",
    @JsonProperty("STEEMIT_INIT_PUBLIC_KEY_STR")
    String steemitInitPublicKeyStr; //GLS7KVuKX87DK44xmhAD92hqJeR8Acd1TBKCtVnGLC5VDpER5CtWE",
    @JsonProperty("STEEMIT_INIT_SUPPLY")
    String steemitInitSupply; //43306176000",
    @JsonProperty("STEEMIT_INIT_TIME")
    LocalDateTime steemitInitTime; //1970-01-01T00:00:00",
    @JsonProperty("STEEMIT_IRREVERSIBLE_THRESHOLD")
    int steemitIrreversibleThreshold; //7500,
    @JsonProperty("STEEMIT_LIQUIDITY_APR_PERCENT")
    int steemitLiquidityAprPercent; //750,
    @JsonProperty("STEEMIT_LIQUIDITY_REWARD_BLOCKS")
    int steemitLiquidityRewardBlocks; //1200,
    @JsonProperty("STEEMIT_LIQUIDITY_REWARD_PERIOD_SEC")
    int steemitLiquidityRewardPeriodSec; //3600,
    @JsonProperty("STEEMIT_LIQUIDITY_TIMEOUT_SEC")
    String steemitLiquidityTimeoutSec; //604800000000",
    @JsonProperty("STEEMIT_MAX_ACCOUNT_NAME_LENGTH")
    int steemitMaxAccountNameLength; //16,
    @JsonProperty("STEEMIT_MAX_ACCOUNT_WITNESS_VOTES")
    int steemitMaxAccountWitnessVotes; //30,
    @JsonProperty("STEEMIT_MAX_ASSET_WHITELIST_AUTHORITIES")
    int steemitMaxAssetWhitelistAuthorities; //10,
    @JsonProperty("STEEMIT_MAX_AUTHORITY_MEMBERSHIP")
    int steemitMaxAuthorityMembership; //10,
    @JsonProperty("STEEMIT_MAX_BLOCK_SIZE")
    int steemitMaxBlockSize; //393216000,
    @JsonProperty("STEEMIT_MAX_CASHOUT_WINDOW_SECONDS")
    int steemitMaxCashoutWindowSeconds; //1209600,
    @JsonProperty("STEEMIT_MAX_COMMENT_DEPTH")
    int steemitMaxCommentDepth; //65520,
    @JsonProperty("STEEMIT_MAX_FEED_AGE")
    String steemitMaxFeedAge; //604800000000",
    @JsonProperty("STEEMIT_MAX_INSTANCE_ID")
    String steemitMaxInstanceId; //281474976710655",

    @JsonProperty("STEEMIT_MAX_MEMO_SIZE")
    int steemitMaxMemoSize; //2048,
    @JsonProperty("STEEMIT_MAX_WITNESSES")
    int steemitMaxWitnesses; //21,
    @JsonProperty("STEEMIT_MAX_MINER_WITNESSES")
    int steemitMaxMinerWitnesses; //1,
    @JsonProperty("STEEMIT_MAX_PROXY_RECURSION_DEPTH")
    int steemitMaxProxyRecursionDepth; //4,
    @JsonProperty("STEEMIT_MAX_RATION_DECAY_RATE")
    int steemitMaxRationDecayRate; //1000000,
    @JsonProperty("STEEMIT_MAX_RESERVE_RATIO")
    int steemitMaxReserveRatio; //20000,
    @JsonProperty("STEEMIT_MAX_RUNNER_WITNESSES")
    int steemitMaxRunnerWitnesses; //1,
    @JsonProperty("STEEMIT_MAX_SHARE_SUPPLY")
    String steemitMaxShareSupply; //1000000000000000",
    @JsonProperty("STEEMIT_MAX_SIG_CHECK_DEPTH")
    int steemitMaxSigCheckDepth; //2,
    @JsonProperty("STEEMIT_MAX_TIME_UNTIL_EXPIRATION")
    int steemitMaxTimeUntilExpiration; //3600,
    @JsonProperty("STEEMIT_MAX_TRANSACTION_SIZE")
    int steemitMaxTransactionSize; //65536,
    @JsonProperty("STEEMIT_MAX_UNDO_HISTORY")
    int steemitMaxUndoHistory; //10000,
    @JsonProperty("STEEMIT_MAX_URL_LENGTH")
    int steemitMaxUrlLength; //127,
    @JsonProperty("STEEMIT_MAX_VOTE_CHANGES")
    int steemitMaxVoteChanges; //5,
    @JsonProperty("STEEMIT_MAX_VOTED_WITNESSES")
    int steemitMaxVotedWitnesses; //19,
    @JsonProperty("STEEMIT_MAX_WITHDRAW_ROUTES")
    int steemitMaxWithdrawRoutes; //10,
    @JsonProperty("STEEMIT_MAX_WITNESS_URL_LENGTH")
    int steemitMaxWitnessUrlLength; //2048,
    @JsonProperty("STEEMIT_MIN_ACCOUNT_CREATION_FEE")
    int steemitMinAccountCreationFee; //1,
    @JsonProperty("STEEMIT_MIN_ACCOUNT_NAME_LENGTH")
    int steemitMinAccountNameLength; //3,
    @JsonProperty("STEEMIT_MIN_BLOCK_SIZE_LIMIT")
    int steemitMinBlockSizeLimit; //65536,
    @JsonProperty("STEEMIT_MIN_CONTENT_REWARD")
    Asset steemitMinContentReward; //1.500 GOLOS",
    @JsonProperty("STEEMIT_MIN_CURATE_REWARD")
    Asset steemitMinCurateReward; //0.500 GOLOS",
    @JsonProperty("STEEMIT_MINER_ACCOUNT")
    String steemitMinerAccount; //miners",
    @JsonProperty("STEEMIT_MINER_PAY_PERCENT")
    int steemitMinerPayPercent; //100,
    @JsonProperty("STEEMIT_MIN_FEEDS")
    int steemitMinFeeds; //7,
    @JsonProperty("STEEMIT_MINING_REWARD")
    Asset steemitMiningReward; //0.666 GOLOS",
    @JsonProperty("STEEMIT_MINING_TIME")
    LocalDateTime steemitMiningTime; //2016-03-24T17:00:00",
    @JsonProperty("STEEMIT_MIN_LIQUIDITY_REWARD")
    Asset steemitMinLiquidityReward; //1200.000 GOLOS",
    @JsonProperty("STEEMIT_MIN_LIQUIDITY_REWARD_PERIOD_SEC")
    int steemitMinLiquidityRewardPeriodSec; //60000000,
    @JsonProperty("STEEMIT_MIN_PAYOUT_SBD")
    Asset steemitMinPayoutSbd; //0.020 GBG",
    @JsonProperty("STEEMIT_MIN_POW_REWARD")
    Asset steemitMinPowReward; //0.666 GOLOS",
    @JsonProperty("STEEMIT_MIN_PRODUCER_REWARD")
    Asset steemitMinProducerReward; //0.666 GOLOS",
    @JsonProperty("STEEMIT_MIN_RATION")
    int steemitMinRation; //100000,
    @JsonProperty("STEEMIT_MIN_TRANSACTION_EXPIRATION_LIMIT")
    int steemitMinTransactionExpirationLimit; //15,
    @JsonProperty("STEEMIT_MIN_TRANSACTION_SIZE_LIMIT")
    int steemitMinTransactionSizeLimit; //1024,
    @JsonProperty("STEEMIT_MIN_UNDO_HISTORY")
    int steemitMinUndoHistory; //10,
    @JsonProperty("STEEMIT_NULL_ACCOUNT")
    String steemitNullAccount; //null",
    @JsonProperty("STEEMIT_NUM_INIT_MINERS")
    int steemitNumInitMiners; //1,
    @JsonProperty("STEEMIT_POW_APR_PERCENT")
    int steemitPowAprPercent; //750,
    @JsonProperty("STEEMIT_PRODUCER_APR_PERCENT")
    int steemitProducerAprPercent; //750,
    @JsonProperty("STEEMIT_PROXY_TO_SELF_ACCOUNT")
    String steemitProxyToSelfAccount; //",
    @JsonProperty("STEEMIT_SBD_INTEREST_COMPOUND_INTERVAL_SEC")
    int steemitSbdInterestCompoundIntervalSec; //2592000,
    @JsonProperty("STEEMIT_SECONDS_PER_YEAR")
    int steemitSecondsPerYear; //31536000,
    @JsonProperty("STEEMIT_REVERSE_AUCTION_WINDOW_SECONDS")
    int steemitReverseAuctionWindowSeconds; //1800,
    @JsonProperty("STEEMIT_START_MINER_VOTING_BLOCK")
    int steemitStartMinerVotingBlock; //200,
    @JsonProperty("STEEMIT_START_VESTING_BLOCK")
    int steemitStartVestingBlock; //1411200,
    @JsonProperty("STEEMIT_SYMBOL")
    String steemitSymbol; //GOLOS",
    @JsonProperty("STEEMIT_TEMP_ACCOUNT")
    String steemitTempAccount; //temp",
    @JsonProperty("STEEMIT_UPVOTE_LOCKOUT")
    int steemitUpvoteLockout; //60000000,
    @JsonProperty("STEEMIT_VESTING_WITHDRAW_INTERVALS")
    int steemitVestingWithdrawIntervals; //4,
    @JsonProperty("STEEMIT_VESTING_WITHDRAW_INTERVAL_SECONDS")
    int steemitVestingWithdrawIntervalSeconds; //604800,
    @JsonProperty("STEEMIT_VOTE_CHANGE_LOCKOUT_PERIOD")
    int steemitVoteChangeLockoutPeriod; //7200,
    @JsonProperty("STEEMIT_VOTE_REGENERATION_SECONDS")
    int steemitVoteRegenerationSeconds; //432000,
    @JsonProperty("STEEM_SYMBOL")
    String steemSymbol; //91600047785731",
    @JsonProperty("VESTS_SYMBOL")
    String vestsSymbol; //91621639407366",
    @JsonProperty("BLOCKCHAIN_NAME")
    String blockchainName; //GOLOS"
}
