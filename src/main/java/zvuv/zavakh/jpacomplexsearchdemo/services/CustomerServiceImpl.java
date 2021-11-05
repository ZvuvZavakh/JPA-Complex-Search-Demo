package zvuv.zavakh.jpacomplexsearchdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import zvuv.zavakh.jpacomplexsearchdemo.domain.Customer;
import zvuv.zavakh.jpacomplexsearchdemo.repositories.CustomerRepository;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.CustomerSpecification;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.SearchCriteria;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.CustomerDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.CustomerPageDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.FilterDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.exceptions.ResourceNotFoundException;
import zvuv.zavakh.jpacomplexsearchdemo.web.mappers.CustomerMapper;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends GenericPaginationService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerPageDto filter(HashMap<String, String> params, FilterDto filterDto) {
        PageRequest pageRequest = getPageRequestFromParams(params);
        List<SearchCriteria> searchCriteriaList = getSearchCriteriaList(filterDto, Customer.class);
        Page<Customer> customers;

        if (searchCriteriaList.size() >= 1) {
            Specification<Customer> resultSpecification = new CustomerSpecification(searchCriteriaList.get(0));

            for (int i = 1; i < searchCriteriaList.size(); i++) {
                resultSpecification = Specification.where(resultSpecification).and(new CustomerSpecification(searchCriteriaList.get(i)));
            }

            customers = customerRepository.findAll(resultSpecification, pageRequest);
        } else {
            customers = customerRepository.findAll(pageRequest);
        }

        return CustomerPageDto
                .builder()
                .pageNumber(pageRequest.getPageNumber())
                .pageSize(pageRequest.getPageSize())
                .totalPages(customers.getTotalPages())
                .totalElements(customers.getTotalElements())
                .items(
                        customers.getContent().stream()
                                .map(customerMapper::convertEntityToDto)
                                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public CustomerDto findById(UUID id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return customerMapper.convertEntityToDto(customer);
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerMapper.convertDtoToEntity(customerDto);

        customerRepository.save(customer);

        return customerMapper.convertEntityToDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto, UUID id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAge(customerDto.getAge());
        customer.setIsActive(customerDto.getIsActive());
        customerRepository.save(customer);

        return customerMapper.convertEntityToDto(customer);
    }

    @Override
    public void delete(UUID id) {
        customerRepository.deleteById(id);
    }
}
