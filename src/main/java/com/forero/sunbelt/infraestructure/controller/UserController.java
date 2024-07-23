package com.forero.sunbelt.infraestructure.controller;

import com.forero.sunbelt.application.command.GetUserCommand;
import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.mapper.UserMapper;
import com.forero.sunbelt.openapi.api.CustomerApi;
import com.forero.sunbelt.openapi.api.model.UserRequestDto;
import com.forero.sunbelt.openapi.api.model.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${openapi.aPIDocumentation.base-path}")
public class UserController implements CustomerApi {
    private static final String LOGGER_PREFIX = String.format("[%s] ", UserController.class.getSimpleName());
    private final GetUserCommand getUserCommand;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponseDto> getCustomer(final UserRequestDto userRequestDto) {
        log.info(LOGGER_PREFIX + "[getCustomer] Request {}", userRequestDto);
        final User user = this.userMapper.toModel(userRequestDto);
        final User userResult = this.getUserCommand.getUser(user);
        final UserResponseDto userResponseDto = this.userMapper.toDto(userResult);
        log.info(LOGGER_PREFIX + "[getCustomer] Response {}", userResponseDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
