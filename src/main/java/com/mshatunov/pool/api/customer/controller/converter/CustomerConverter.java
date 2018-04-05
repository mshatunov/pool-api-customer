package com.mshatunov.pool.api.customer.controller.converter;

import com.mshatunov.pool.api.customer.controller.dto.CustomerDTO;
import com.mshatunov.pool.api.customer.model.Customer;

import java.util.List;

public interface CustomerConverter {
    CustomerDTO convertCustomerToDTO(Customer customer);
    List<CustomerDTO> convertCustomersToDTO(List<Customer> customers);
}
