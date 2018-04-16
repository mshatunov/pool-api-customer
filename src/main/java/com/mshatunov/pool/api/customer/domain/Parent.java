package com.mshatunov.pool.api.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    private String name;
    private String lastName;
    private String middleName;
    private Map<ContactType, String> contacts;
}
