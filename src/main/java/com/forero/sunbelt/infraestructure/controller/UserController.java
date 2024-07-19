package com.forero.sunbelt.infraestructure.controller;

import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.mapper.UserMapper;
import com.forero.sunbelt.infraestructure.services.dto.UserRequestDto;
import com.forero.sunbelt.infraestructure.services.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class UserController {
    private static final String LOGGER_PREFIX = String.format("[%s] ", UserController.class.getSimpleName());
    private final UserMapper userMapper;

    @PostMapping("/customer")
    public ResponseEntity<UserResponseDto> getUser(final UserRequestDto userRequestDto) {
        log.info(LOGGER_PREFIX + "[getUser] Request {}", userRequestDto);
        final User user = this.userMapper.toModel(userRequestDto);
        return null;
    }
}
