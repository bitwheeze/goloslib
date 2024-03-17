package bitwheeze.golos.goloslib.model.nft;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NftTokenQuery {
    String owner;
    @JsonProperty("start_token_id")
    Integer startTokenId;
    Integer limit; // принцип работы пагинации описан на примере getNftCollections

    @JsonProperty("select_collections")
    String[] selectCollections; // выбирает токены из заданных коллекций
    @JsonProperty("collections_limit")
    Integer collectionLimit; // Например - чтобы в списке коллекций (getNftCollections) добавить к каждой коллекции информацию о первом токене в ней

    @JsonProperty("select_token_ids")
    Integer[] selectTokenIds; // выборка конкретных токенов

    @JsonProperty("filter_creators")
    String[] filterCreators;

    @JsonProperty("filter_token_ids")
    Integer[] filterTokenIds;

    @JsonProperty("filter_names")
    String [] filterNames; // игнорировать токены из этих коллекций

    public enum TokenState {
        any, selling_one, not_selling_one;
    }

    TokenState state; // или 'selling_one' - только токены которые выставлены на продажу, или 'not_selling_one' - только которые не выставлены

    public enum TokenSortOrder {
        by_name, by_issued, by_last_update, by_last_price;
    }

    TokenSortOrder sort; // by_issued, by_last_update, by_last_price
    @JsonProperty("reverse_sort")
    Boolean reverseSort;
}
