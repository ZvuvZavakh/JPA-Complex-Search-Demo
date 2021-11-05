package zvuv.zavakh.jpacomplexsearchdemo.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zvuv.zavakh.jpacomplexsearchdemo.domain.Customer;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.CustomerDto;

@Mapper(uses = {DateMapper.class}, componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto convertEntityToDto(Customer customer);
    Customer convertDtoToEntity(CustomerDto customerDto);
}
