package zvuv.zavakh.jpacomplexsearchdemo.specifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;
}
