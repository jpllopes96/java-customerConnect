package tech.jplopes.customerconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jplopes.customerconnect.controller.dto.ApiResponse;
import tech.jplopes.customerconnect.controller.dto.CreateCustomerDto;
import tech.jplopes.customerconnect.controller.dto.PaginationResponse;
import tech.jplopes.customerconnect.controller.dto.UpdateCustomerDto;
import tech.jplopes.customerconnect.entity.CustomerEntity;
import tech.jplopes.customerconnect.service.CustomerService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerDto dto){

        var customer = customerService.createCustomer(dto);

        return ResponseEntity.created(URI.create("/customers/"+ customer.getCustomerId())).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerEntity>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                               @RequestParam(name = "orderBy", defaultValue = "desc") String orderBy,
                                                               @RequestParam(name = "cpf", required = false) String cpf,
                                                               @RequestParam(name = "email", required = false) String email){

        var pageResp = customerService.findAll(page, pageSize, orderBy, cpf, email);

        return ResponseEntity.ok(new ApiResponse<>(
                pageResp.getContent(),
                new PaginationResponse(pageResp.getNumber(), pageResp.getSize(), pageResp.getTotalElements(), pageResp.getTotalPages())
        ));

    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable("customerId") Long customerId){

        var customer = customerService.findById(customerId);

        return customer.isPresent() ?
                ResponseEntity.ok(customer.get()) :
                ResponseEntity.notFound().build();

    }

    @PutMapping(path = "/{customerId}" )
    public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable("customerId") Long customerId,
                                                         @RequestBody UpdateCustomerDto dto){

        var customer = customerService.updateCustomer(customerId, dto);

        return customer.isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping(path = "/{customerId}" )
    public ResponseEntity<CustomerEntity> deleteById(@PathVariable("customerId") Long customerId){

        var deleted = customerService.deleteCustomer(customerId);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();

    }

}
