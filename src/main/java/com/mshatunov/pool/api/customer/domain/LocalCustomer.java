package com.mshatunov.pool.api.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalCustomer {
    private String id;
    private String name;
    private String lastName;
    private String middleNaame;
    private Map<ContactType, String> contacts;
    private List<Parent> parents;
}
