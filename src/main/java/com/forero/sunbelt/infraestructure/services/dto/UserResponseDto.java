package com.forero.sunbelt.infraestructure.services.dto;


public record UserResponseDto(
        String firstName,
        String secondName,
        String surname,
        String secondSurname,
        String phone,
        String address,
        String city
) {
}
