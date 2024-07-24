package com.forero.sunbelt.infraestructure.services.adapter;

import com.forero.sunbelt.application.exception.RepositoryException;
import com.forero.sunbelt.application.service.RepositoryService;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.mapper.UserMapper;
import com.forero.sunbelt.infraestructure.services.entity.UserEntity;
import com.forero.sunbelt.infraestructure.services.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class H2Adapter implements RepositoryService {
    private static final String LOGGER_PREFIX = String.format("[%s] ", H2Adapter.class.getSimpleName());
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUser(final String documentNumber) {
        log.info(LOGGER_PREFIX + "[getUser] Request {}", documentNumber);
        final UserEntity userEntityOptional = this.userRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new RepositoryException(CodeException.CUSTOMER_NOT_FOUND, null));
        log.info(LOGGER_PREFIX + "[getUser] Response {}", userEntityOptional);
        return this.userMapper.toModelEntity(userEntityOptional);
    }
}
