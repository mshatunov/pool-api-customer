package com.mshatunov.pool.api.customer.controller;

import com.mshatunov.pool.api.customer.controller.converter.CustomerConverter;
import com.mshatunov.pool.api.customer.controller.dto.CustomerDTO;
import com.mshatunov.pool.api.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String GET_URI = "{customerId}";

    private final CustomerService service;
    private final CustomerConverter converter;

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return converter.convertCustomersToDTO(service.getCustomers());
    }

    @GetMapping(value = GET_URI)
    public CustomerDTO getCustomer(@PathVariable("customerId") String customerId) {
        return converter.convertCustomerToDTO(service.getCustomer(customerId));
    }

}
