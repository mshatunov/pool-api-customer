package com.mshatunov.pool.api.customer.service;

import com.mshatunov.pool.api.customer.domain.Customer;
import com.mshatunov.pool.api.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Customer getCustomer(String customerId) {
        Optional<Customer> customer = repository.findById(customerId);
        return customer.isPresent() ? null : Customer.builder()
                .id(customer.get().getId())
                .name(customer.get().getName())
                .build();
    }

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll();
    }
}
