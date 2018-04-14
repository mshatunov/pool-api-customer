package com.mshatunov.pool.api.customer.repository.model;

import com.mshatunov.pool.api.customer.domain.ContactType;
import com.mshatunov.pool.api.customer.domain.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String middleName;
    private Map<ContactType, String> contacts;
    private List<Parent> parents;
}
