package com.mshatunov.pool.api.customer.service;

import com.mshatunov.pool.api.customer.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(String customerId);
    List<Customer> getCustomers();
}
