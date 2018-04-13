package com.mshatunov.pool.api.customer.controller.converter;

import com.mshatunov.pool.api.customer.controller.dto.CustomerCreateRequest;
import com.mshatunov.pool.api.customer.controller.dto.CustomerResponse;
import com.mshatunov.pool.api.customer.domain.LocalCustomer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface
CustomerConverter {
    CustomerResponse convertCustomerToResponse(LocalCustomer customer);
    List<CustomerResponse> convertCustomersToResponse(List<LocalCustomer> customers);
    LocalCustomer convertCustomerDTOtoCustomer(CustomerCreateRequest customer);
}

