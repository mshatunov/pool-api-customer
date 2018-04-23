package com.mshatunov.pool.api.customer.service;

import com.mshatunov.pool.api.customer.domain.LocalCustomer;
import com.mshatunov.pool.api.customer.exception.CustomerNotFoundException;
import com.mshatunov.pool.api.customer.repository.CustomerRepository;
import com.mshatunov.pool.api.customer.repository.model.Customer;
import com.mshatunov.pool.api.customer.service.converter.LocalCustomerConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final LocalCustomerConverter converter;

    @Override
    public LocalCustomer getCustomer(String customerId) {
        return repository.findById(customerId)
                .map(converter::convertCustomerToLocalCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public List<LocalCustomer> getCustomers() {
        return converter.convertCustomersToLocalCustomers(repository.findAll());
    }

    @Override
    public String saveCustomer(LocalCustomer customer) {
        Customer savedCustomer = repository.save(converter.convertLocalCustomerToCustomer(customer));
        return savedCustomer.getId();
    }

    @Override
    public void updateCustomer(String customerId, LocalCustomer customer) {
        if (!repository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        customer.setId(customerId);
        repository.save(converter.convertLocalCustomerToCustomer(customer));
    }

    @Override
    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}

