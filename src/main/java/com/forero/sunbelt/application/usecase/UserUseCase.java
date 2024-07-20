package com.forero.sunbelt.application.usecase;

import com.forero.sunbelt.application.service.RepositoryService;
import com.forero.sunbelt.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase {
    private final RepositoryService repositoryService;

    public User getUser(final User user) {
        return repositoryService.getUser(user);
    }
}
