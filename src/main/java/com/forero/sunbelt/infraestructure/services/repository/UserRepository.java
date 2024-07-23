package com.forero.sunbelt.infraestructure.services.repository;

import com.forero.sunbelt.infraestructure.services.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDocumentNumber(String documentNumber);
}
