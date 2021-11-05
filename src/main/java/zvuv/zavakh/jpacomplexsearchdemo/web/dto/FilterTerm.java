package zvuv.zavakh.jpacomplexsearchdemo.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.FilteringTypes;

@JsonDeserialize(using = FilterTermDeserializer.class)
public interface FilterTerm {

    String getField();
    FilteringTypes getOperation();
    Object getValue();
}
