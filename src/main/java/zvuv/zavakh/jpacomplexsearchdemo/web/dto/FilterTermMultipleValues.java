package zvuv.zavakh.jpacomplexsearchdemo.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.FilteringTypes;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(as = FilterTermMultipleValues.class)
public class FilterTermMultipleValues implements FilterTerm {

    private String field;
    private List<String> value;
    private FilteringTypes operation;
}
