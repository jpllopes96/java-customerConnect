package tech.jplopes.customerconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.jplopes.customerconnect.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
