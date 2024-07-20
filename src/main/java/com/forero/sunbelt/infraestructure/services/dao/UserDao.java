package com.forero.sunbelt.infraestructure.services.dao;

import com.forero.sunbelt.application.exception.RepositoryException;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public User findByDocument(String documentNumber) {
        if ("10121314".equals(documentNumber)) {
            return User.builder()
                    .firstName("Juan")
                    .phone("1234567890")
                    .city("Bogota")
                    .address("Calle Falsa 123")
                    .secondName("Carlos")
                    .surname("Perez")
                    .secondSurname("Gomez")
                    .build();
        }
        throw new RepositoryException(CodeException.CUSTOMER_NOT_FOUND, null);
    }
}
