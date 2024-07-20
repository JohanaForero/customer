package com.forero.sunbelt.infraestructure.services.dao;

import com.forero.sunbelt.application.exception.RepositoryException;
import com.forero.sunbelt.application.service.RepositoryService;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepositoryImplementation implements RepositoryService {
    private static final String LOGGER_PREFIX = String.format("[%s] ", RepositoryImplementation.class.getSimpleName());
    private final UserMapper userMapper;
    private final UserDao userDao;

    @Override
    public User getUser(final String numeroDocumento) {
        try {
            log.info(LOGGER_PREFIX + "[getUser] Request {}", numeroDocumento);
            final User userResult = this.userDao.findByDocument(numeroDocumento);
            log.info(LOGGER_PREFIX + "[getUser] Response {}", userResult);
            return userResult;
        } catch (Exception exception) {
            log.info(LOGGER_PREFIX + "[getUser] User not found", exception.getMessage());
            throw new RepositoryException(CodeException.CUSTOMER_NOT_FOUND, exception);
        }
    }
}
