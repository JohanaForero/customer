package com.forero.sunbelt.infraestructure.services.dao;

import com.forero.sunbelt.infraestructure.services.dto.UserResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public UserResponseDto findByDocument(String documentType, String documentNumber) {
        if ("C".equals(documentType) && "10121314".equals(documentNumber)) {
            return new UserResponseDto("Juan", "Carlos", "Pérez", "Gómez",
                    "1234567890", "Calle Falsa 123", "Bogotá");
        }
        return null;
    }
}
