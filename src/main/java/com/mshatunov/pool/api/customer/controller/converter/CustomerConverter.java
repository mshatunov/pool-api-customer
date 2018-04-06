package com.mshatunov.pool.api.customer.controller.converter;

import com.mshatunov.pool.api.customer.controller.dto.CustomerDTO;
import com.mshatunov.pool.api.customer.domain.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerConverter {
    CustomerDTO convertCustomerToDTO(Customer customer);
    List<CustomerDTO> convertCustomersToDTO(List<Customer> customers);
}
