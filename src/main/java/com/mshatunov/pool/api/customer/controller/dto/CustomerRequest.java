package com.mshatunov.pool.api.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mshatunov.pool.api.customer.domain.ContactType;
import com.mshatunov.pool.api.customer.domain.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    private String middleName;
    private Map<ContactType, String> contacts;
    private List<Parent> parents;
}
