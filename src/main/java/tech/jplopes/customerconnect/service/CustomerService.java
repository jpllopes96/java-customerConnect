package tech.jplopes.customerconnect.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.jplopes.customerconnect.controller.dto.CreateCustomerDto;
import tech.jplopes.customerconnect.entity.CustomerEntity;
import tech.jplopes.customerconnect.repository.CustomerRepository;

import static org.springframework.util.StringUtils.*;

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

    public Page<CustomerEntity> findAll(Integer page, Integer pageSize, String orderBy, String cpf, String email) {

       var pageRequest = getPageRequest(page, pageSize, orderBy);

        return findWithFilter(cpf, email, pageRequest);
    }

    private Page<CustomerEntity> findWithFilter(String cpf, String email, PageRequest pageRequest) {
        if(hasText(cpf) && (hasText(email))){
            return customerRepository.findByCpfAndEmail(cpf, email, pageRequest);
        }

        if(hasText(cpf)){
            return customerRepository.findByCpf(cpf, pageRequest);
        }

        if (hasText(email)){
            return customerRepository.findByEmail(email, pageRequest);
        }

        return customerRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(Integer page, Integer pageSize, String orderBy) {
        var direction = Sort.Direction.DESC;
        if(orderBy.equalsIgnoreCase("asc")){
            direction = Sort.Direction.ASC;
        }

         return PageRequest.of(page, pageSize, direction, "createdAt");
    }
}
