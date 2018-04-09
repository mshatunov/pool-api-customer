package com.mshatunov.pool.api.customer.service.converter;

import com.mshatunov.pool.api.customer.domain.LocalCustomer;
import com.mshatunov.pool.api.customer.repository.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocalCustomerConverter {
    List<LocalCustomer> convertCustomersToLocalCustomers(List<Customer> customers);
    Customer convertLocalCustomerToCustomer(LocalCustomer localCustomer);
    LocalCustomer convertCustomerToLocalCustomer(Customer customer);
}
