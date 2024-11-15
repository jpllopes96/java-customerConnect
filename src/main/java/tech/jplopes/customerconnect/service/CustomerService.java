package tech.jplopes.customerconnect.service;

import org.springframework.stereotype.Service;
import tech.jplopes.customerconnect.controller.dto.CreateCustomerDto;
import tech.jplopes.customerconnect.entity.CustomerEntity;
import tech.jplopes.customerconnect.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerEntity createCustomer(CreateCustomerDto dto) {

        var entity = new CustomerEntity();
        entity.setFullName(dto.fullName());
        entity.setCpf(dto.cpf());
        entity.setEmail(dto.email());
        entity.setPhoneNumber(dto.phoneNumber());

        return customerRepository.save(entity);

    }
}
