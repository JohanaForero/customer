package com.forero.sunbelt.infraestructure.services.dao;

import com.forero.sunbelt.application.service.RepositoryService;
import com.forero.sunbelt.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepositoryImplementation implements RepositoryService {
    private static final String LOGGER_PREFIX = String.format("[%s] ", RepositoryImplementation.class.getSimpleName());
    private final UserDao userDao;

    @Override
    public User getUser(final String documentNumber) {
        log.info(LOGGER_PREFIX + "[getUser] Request {}", documentNumber);
        final User userResult = this.userDao.findByDocument(documentNumber);
        log.info(LOGGER_PREFIX + "[getUser] Response {}", userResult);
        return userResult;
    }
}
