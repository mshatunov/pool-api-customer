package com.mshatunov.pool.api.customer.controller;

import com.mshatunov.pool.api.customer.controller.converter.CustomerConverter;
import com.mshatunov.pool.api.customer.controller.dto.CustomerDTO;
import com.mshatunov.pool.api.customer.controller.dto.CustomerResponse;
import com.mshatunov.pool.api.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String GET_URI = "{customerId}";

    private final CustomerService service;
    private final CustomerConverter converter;

    @PostMapping
    public void createCustomer(@Validated @RequestBody CustomerDTO customer) {
        service.saveCustomer(converter.convertCustomerDTOtoCustomer(customer));
    }

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return converter.convertCustomersToResponse(service.getCustomers());
    }

    @GetMapping(value = GET_URI)
    public CustomerResponse getCustomer(@PathVariable("customerId") String customerId) {
        return converter.convertCustomerToResponse(service.getCustomer(customerId));
    }

}
