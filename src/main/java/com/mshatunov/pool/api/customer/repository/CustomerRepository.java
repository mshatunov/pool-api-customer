package com.mshatunov.pool.api.customer.repository;

import com.mshatunov.pool.api.customer.repository.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
