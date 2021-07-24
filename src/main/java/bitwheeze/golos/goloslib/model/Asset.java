package bitwheeze.golos.goloslib.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
	public static Asset EMPTY = new Asset();
	
	private BigDecimal value = BigDecimal.ZERO;
    private String asset = "NOTSET";
	
	public static Asset fromString(String string) {
		if(null == string || string.isBlank()) {
			return EMPTY;
		}
		
		String[] pair = string.split(" ");
		if(pair.length != 2) {
			return EMPTY;
		}
		
		Asset ret = new Asset();
		ret.setAsset(pair[1]);
		ret.setValue(new BigDecimal(pair[0]));
		return ret;
	}
	
	@JsonValue
	public String toString() {
		return value.toString() + " " + asset;
	}
	
	public static void main(String[] args) {
		System.out.println("Asset " + Asset.fromString("12.123 GOLOS"));
		System.out.println("Asset " + Asset.fromString("0 GOLOS"));
		System.out.println("Asset " + Asset.fromString("GOLOS"));
		System.out.println("Asset " + Asset.fromString(""));
		System.out.println("Asset " + Asset.fromString(null) + " " + (Asset.fromString(null) == EMPTY));
	}
}
