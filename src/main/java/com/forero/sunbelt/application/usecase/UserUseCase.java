package com.forero.sunbelt.application.usecase;

import com.forero.sunbelt.application.exception.UserUseCaseException;
import com.forero.sunbelt.application.service.RepositoryService;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.domain.model.DocumentType;
import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.controller.UserController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record UserUseCase(RepositoryService repositoryService) {
    private static final String LOGGER_PREFIX = String.format("[%s] ", UserController.class.getSimpleName());
    private static final String LOG_CHECK_USER_FIELDS = "{} [checkUserFields] Request {}";

    public User getUser(final User user) {
        this.checkUserFields(user);
        return repositoryService.getUser(user.documentNumber());
    }

    private void checkUserFields(final User user) {
        final DocumentType documentType = user.documentType();
        final String documentNumber = user.documentNumber();

        if (documentType == null) {
            log.info(LOG_CHECK_USER_FIELDS, LOGGER_PREFIX, "null");
            throw new UserUseCaseException(CodeException.INVALID_PARAMETERS, null, "documentType");
        }

        if (documentNumber == null) {
            log.info(LOG_CHECK_USER_FIELDS, LOGGER_PREFIX, null);
            throw new UserUseCaseException(CodeException.INVALID_PARAMETERS, null, "documentNumber");
        }

        if (!isValidDocumentType(documentType)) {
            log.info(LOG_CHECK_USER_FIELDS, LOGGER_PREFIX, documentType);
            throw new UserUseCaseException(CodeException.INVALID_TYPE_DOCUMENT, null);
        }
    }

    private boolean isValidDocumentType(DocumentType documentType) {
        return documentType == DocumentType.C || documentType == DocumentType.P;
    }
}
