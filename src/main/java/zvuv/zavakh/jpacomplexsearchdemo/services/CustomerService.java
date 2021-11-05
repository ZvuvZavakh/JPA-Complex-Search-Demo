package zvuv.zavakh.jpacomplexsearchdemo.services;

import org.springframework.data.domain.PageRequest;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.CustomerDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.CustomerPageDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.FilterDto;

import java.util.HashMap;
import java.util.UUID;

public interface CustomerService {

    CustomerPageDto filter(HashMap<String, String> params, FilterDto filterDto);
    CustomerDto findById(UUID id);
    CustomerDto create(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto, UUID id);
    void delete(UUID id);
}
