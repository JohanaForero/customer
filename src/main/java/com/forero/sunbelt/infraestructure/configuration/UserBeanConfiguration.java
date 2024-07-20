package com.forero.sunbelt.infraestructure.configuration;

import com.forero.sunbelt.application.command.GetUserCommand;
import com.forero.sunbelt.application.service.RepositoryService;
import com.forero.sunbelt.application.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class UserBeanConfiguration {

    @Bean
    public GetUserCommand getUserCommand(final UserUseCase userUseCase) {
        return new GetUserCommand(userUseCase);
    }

    @Bean
    public UserUseCase userUseCase(final RepositoryService repositoryService) {
        return new UserUseCase(repositoryService);
    }
}
