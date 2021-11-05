package zvuv.zavakh.jpacomplexsearchdemo.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import zvuv.zavakh.jpacomplexsearchdemo.domain.Customer;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CustomerPageDto {

    @JsonProperty("totalPages")
    private final Integer totalPages;

    @JsonProperty("totalElements")
    private final Long totalElements;

    @JsonProperty("items")
    private final List<CustomerDto> items;

    @JsonProperty("pageNumber")
    private final Integer pageNumber;

    @JsonProperty("pageSize")
    private final Integer pageSize;
}
