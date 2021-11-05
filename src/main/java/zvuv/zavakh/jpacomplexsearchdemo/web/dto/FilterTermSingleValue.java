package zvuv.zavakh.jpacomplexsearchdemo.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.FilteringTypes;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(as = FilterTermSingleValue.class)
public class FilterTermSingleValue implements FilterTerm {

    private String field;
    private String value;
    private FilteringTypes operation;
}
