package com.mshatunov.pool.api.customer.service;

import com.mshatunov.pool.api.customer.domain.LocalCustomer;
import com.mshatunov.pool.api.customer.repository.CustomerRepository;
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
                .orElse(null);
    }

    @Override
    public List<LocalCustomer> getCustomers() {
        return converter.convertCustomersToLocalCustomers(repository.findAll());
    }

    @Override
    public void saveCustomer(LocalCustomer customer) {
        repository.save(converter.convertLocalCustomerToCustomer(customer));
    }
}
