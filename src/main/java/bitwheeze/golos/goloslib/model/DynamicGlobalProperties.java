package bitwheeze.golos.goloslib.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DynamicGlobalProperties {
	private long id;
	private long headBlockNumber;
	private String headBlockId;
	private LocalDateTime time;
	private String currentWitness;
	private long totalPow;
	private long numPowWitnesses;
	private Asset virtualSupply;
	private Asset currentSupply;
	private Asset confidentialSupply;
	private Asset currentSbdSupply;
	private Asset confidentialSbdSupply;
	private Asset totalVestingFundSteem;
	private Asset totalVestingShares;
	private Asset accumulativeBalance;
	private Asset totalRewardFundSteem;
	private long totalRewardShares2;
	private short sbdInterestRate;
	private short sbdPrintRate;
	private short sbdDebtPercent;
	private long averageBlockSize;
	private long maximumBlockSize;
	private long currentAslot;
	private String recentSlotsFilled;
	private long participationCount;
	private long lastIrreversibleBlockNum;
	private long maxVirtualBandwidth;
	private int currentReserveRatio;
	private long customOpsBandwidthMultiplier;
	private boolean isForcedMinPrice;
	private long transitBlockNum;
	private String transitWitnesses;
	private WorkerRequest[] workerRequests;
	private Asset accumulativeEmissionPerDay;

	public static void main(String[] args) {
		DynamicGlobalProperties props = new DynamicGlobalProperties();
	}
	
}
