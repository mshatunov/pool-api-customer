package com.mshatunov.pool.api.customer.controller;

import com.mshatunov.pool.api.customer.controller.converter.CustomerConverter;
import com.mshatunov.pool.api.customer.controller.dto.CustomerRequest;
import com.mshatunov.pool.api.customer.controller.dto.CustomerResponse;
import com.mshatunov.pool.api.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_ID = "{customerId}";
    public static final String CUSTOMER_PATH = "customerId";

    private final CustomerService service;
    private final CustomerConverter converter;

    @PostMapping
    public String createCustomer(@Validated @RequestBody CustomerRequest customer) {
        return service.saveCustomer(converter.convertCustomerRequestToCustomer(customer));
    }

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return converter.convertCustomersToResponse(service.getCustomers());
    }

    @GetMapping(value = CUSTOMER_ID)
    public CustomerResponse getCustomer(@PathVariable(CUSTOMER_PATH) String customerId) {
        return converter.convertCustomerToResponse(service.getCustomer(customerId));
    }

    @PutMapping(value = CUSTOMER_ID)
    public void updateCustomer(@PathVariable(CUSTOMER_PATH) String customerId,
                               @RequestBody CustomerRequest customer) {
        service.updateCustomer(customerId, converter.convertCustomerRequestToCustomer(customer));
    }

    @DeleteMapping(value = CUSTOMER_ID)
    public void deleteCustomer(@PathVariable(CUSTOMER_PATH) String customerId) {
        service.deleteCustomer(customerId);
    }

}
