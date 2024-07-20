package com.forero.sunbelt.application.command;

import com.forero.sunbelt.application.usecase.UserUseCase;
import com.forero.sunbelt.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserCommand {
    private final UserUseCase userUseCase;

    public User getUser(final User user) {
        return this.userUseCase.getUser(user);
    }
}
