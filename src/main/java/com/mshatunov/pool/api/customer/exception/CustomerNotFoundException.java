package com.mshatunov.pool.api.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super("Customer " + customerId + " not found");
    }
}
