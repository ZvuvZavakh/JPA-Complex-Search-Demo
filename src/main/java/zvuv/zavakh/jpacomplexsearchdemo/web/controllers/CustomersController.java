package zvuv.zavakh.jpacomplexsearchdemo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import zvuv.zavakh.jpacomplexsearchdemo.services.CustomerService;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.CustomerDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.CustomerPageDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.FilterDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomersController {

    private final CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/filter")
    public ResponseEntity<CustomerPageDto> filter(@RequestParam HashMap<String, String> pageProperties,
                                                @RequestBody @Nullable FilterDto filterDto) {
        return ResponseEntity.ok(customerService.filter(pageProperties, filterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.create(customerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody @Valid CustomerDto customerDto,
                                              @PathVariable("id") UUID id) {
        return ResponseEntity.ok(customerService.update(customerDto, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        customerService.delete(id);
    }
}
