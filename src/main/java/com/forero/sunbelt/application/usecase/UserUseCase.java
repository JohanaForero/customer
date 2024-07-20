package com.forero.sunbelt.application.usecase;

import com.forero.sunbelt.application.exception.UserUseCaseException;
import com.forero.sunbelt.application.service.RepositoryService;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase {
    private final RepositoryService repositoryService;

    public User getUser(final User user) {
        this.checkUserFields(user);
        return repositoryService.getUser(user);
    }

    private void checkUserFields(final User user) {
        if (user.getDocumentType() == null) {
            throw new UserUseCaseException(CodeException.INVALID_PARAMETERS, null, "documentType");
        }
        if (user.getDocumentNumber() == null) {
            throw new UserUseCaseException(CodeException.INVALID_PARAMETERS, null, "documentNumber");
        }
    }
}
