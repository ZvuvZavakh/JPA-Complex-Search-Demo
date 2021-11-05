package zvuv.zavakh.jpacomplexsearchdemo.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilteringTypes {
    GREATER_THEN(">"),
    GREATER_THEN_OR_EQUAL_TO(">="),
    LESS_THEN("<"),
    LESS_THEN_OR_EQUAL_TO("<="),
    LIKE("like"),
    EQUALS("="),
    NOT_EQUAL("!="),
    IN("in");

    private final String value;
}
