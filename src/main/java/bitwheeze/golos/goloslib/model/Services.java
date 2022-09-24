package bitwheeze.golos.goloslib.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Services {
    Asset post;
    Asset comment;
    Asset vote;
}
