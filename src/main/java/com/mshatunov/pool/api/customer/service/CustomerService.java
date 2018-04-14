package com.mshatunov.pool.api.customer.service;

import com.mshatunov.pool.api.customer.domain.LocalCustomer;

import java.util.List;

public interface CustomerService {
    LocalCustomer getCustomer(String customerId);
    List<LocalCustomer> getCustomers();
    String saveCustomer(LocalCustomer customer);
}
