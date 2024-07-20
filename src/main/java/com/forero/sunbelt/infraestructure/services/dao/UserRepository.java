package com.forero.sunbelt.infraestructure.services.dao;

import com.forero.sunbelt.application.exception.RepositoryException;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public UserDao findByDocument(User user) {
        final String documentType = user.getDocumentType().toUpperCase();
        final String documentNumber = user.getDocumentNumber();
        if (!"C".equals(documentType) && !"P".equals(documentType)) {
            throw new RepositoryException(CodeException.CUSTOMER_NOT_FOUND, null);
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
        throw new RepositoryException(CodeException.CUSTOMER_NOT_FOUND, null);
    }
}
