package com.forero.sunbelt.infraestructure.services.dao;

import com.forero.sunbelt.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public UserDao findByDocument(User user) {
        final String documentType = user.getDocumentType().toUpperCase();
        final String documentNumber = user.getDocumentNumber();
        if (!"C".equals(documentType) && !"P".equals(documentType)) {
            return null;
        }
        if ("C".equals(documentType) && "10121314".equals(documentNumber)) {
            return new UserDao(
                    "Juan",
                    "Carlos",
                    "Perez",
                    "Gomez",
                    "1234567890",
                    "Calle Falsa 123",
                    "Bogota"
            );
        }
        return null;
    }
}
