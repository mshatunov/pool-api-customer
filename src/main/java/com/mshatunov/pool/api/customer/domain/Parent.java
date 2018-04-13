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
public class Parent {
    private String name;
    private String lastNname;
    private String middleNname;
    private List<Contact> contacts;
    private List<Parent> parents;
}
