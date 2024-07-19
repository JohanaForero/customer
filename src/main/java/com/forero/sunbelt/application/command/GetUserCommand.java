package com.forero.sunbelt.application.command;

import com.forero.sunbelt.application.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserCommand {
    private final UserUseCase userUseCase;
}
