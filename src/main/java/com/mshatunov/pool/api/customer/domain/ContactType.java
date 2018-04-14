package com.mshatunov.pool.api.customer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum ContactType {
    @JsonProperty("phone")
    PHONE,
    @JsonProperty("email")
    EMAIL
}
