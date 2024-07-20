package com.forero.sunbelt.domain.model;

import lombok.Builder;

@Builder
public record User(
        String firstName,
        String secondName,
        String surname,
        String secondSurname,
        String phone,
        String address,
        String city,
        String documentType,
        String documentNumber) {
}
