package com.mshatunov.pool.api.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalCustomer {
    private String id;
    private String name;
    private String lastName;
    private String middleNaame;
    private List<Contact> contacts;
    private List<Parent> parents;
}
