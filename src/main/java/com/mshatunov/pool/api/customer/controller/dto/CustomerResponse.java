package com.mshatunov.pool.api.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mshatunov.pool.api.customer.domain.Contact;
import com.mshatunov.pool.api.customer.domain.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerResponse {
    private String id;
    private String name;
    private String lastName;
    private String middleName;
    private List<Contact> contacts;
    private List<Parent> parents;
}
