package com.forero.sunbelt.infraestructure.exception;

import com.forero.sunbelt.application.exception.CoreException;
import com.forero.sunbelt.application.exception.UserUseCaseException;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.openapi.api.model.ErrorObjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.AbstractMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class UserControllerAdvice {
    private static final String LOGGER_PREFIX = String.format("[%s] ", UserControllerAdvice.class.getSimpleName());

    private static final Map<CodeException, HttpStatus> HTTP_STATUS_BY_CODE_EXCEPTION = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(CodeException.INVALID_PARAMETERS, HttpStatus.BAD_REQUEST),
            new AbstractMap.SimpleEntry<>(CodeException.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND),
            new AbstractMap.SimpleEntry<>(CodeException.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
    );

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ErrorObjectDto> handlerException(final CoreException coreException) {
        final CodeException codeException = coreException.getCodeException();

        final ErrorObjectDto errorObjectDto = new ErrorObjectDto();
        errorObjectDto.code(codeException.name());
        errorObjectDto.message(coreException.getMessage());

        final HttpStatus httpStatus = HTTP_STATUS_BY_CODE_EXCEPTION.getOrDefault(codeException, HttpStatus.NOT_EXTENDED);

        return new ResponseEntity<>(errorObjectDto, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObjectDto> handlerException(final Exception exception) {
        log.error(LOGGER_PREFIX + "[handlerException] Unhandled exception", exception);
        final CodeException codeException = CodeException.INTERNAL_SERVER_ERROR;

        final ErrorObjectDto errorObjectDto = new ErrorObjectDto();
        errorObjectDto.code(codeException.name());
        errorObjectDto.message(exception.getMessage());

        final HttpStatus httpStatus = HTTP_STATUS_BY_CODE_EXCEPTION.getOrDefault(codeException, HttpStatus.NOT_EXTENDED);

        return new ResponseEntity<>(errorObjectDto, httpStatus);
    }

    @ExceptionHandler(UserUseCaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidDocumentException(UserUseCaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
