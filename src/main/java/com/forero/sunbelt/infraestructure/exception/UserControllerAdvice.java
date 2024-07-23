package com.forero.sunbelt.infraestructure.exception;

import com.forero.sunbelt.application.exception.CoreException;
import com.forero.sunbelt.application.exception.RepositoryException;
import com.forero.sunbelt.application.exception.UserUseCaseException;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.openapi.api.model.ErrorObjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        log.error(LOGGER_PREFIX + "[handlerException] Unhandled", exception);
        final CodeException codeException = CodeException.INTERNAL_SERVER_ERROR;

        final ErrorObjectDto errorObjectDto = new ErrorObjectDto();
        errorObjectDto.code(codeException.name());
        errorObjectDto.message(exception.getMessage());

        final HttpStatus httpStatus = HTTP_STATUS_BY_CODE_EXCEPTION.getOrDefault(codeException, HttpStatus.NOT_EXTENDED);

        return new ResponseEntity<>(errorObjectDto, httpStatus);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorObjectDto> handlerException(RepositoryException repositoryException) {
        log.error(LOGGER_PREFIX + "[handlerException] Unhandled exception", repositoryException);
        final CodeException codeException = CodeException.CUSTOMER_NOT_FOUND;

        final ErrorObjectDto errorObjectDto = new ErrorObjectDto();
        errorObjectDto.code(codeException.name());
        errorObjectDto.message(repositoryException.getMessage());

        final HttpStatus httpStatus = HTTP_STATUS_BY_CODE_EXCEPTION.getOrDefault(codeException, HttpStatus.NOT_EXTENDED);
        return new ResponseEntity<>(errorObjectDto, httpStatus);
    }

    @ExceptionHandler(UserUseCaseException.class)
    public ResponseEntity<ErrorObjectDto> handlerException(UserUseCaseException userUseCaseException) {
        log.error(LOGGER_PREFIX + "[handlerException] Unhandled exception", userUseCaseException);
        final CodeException codeException = CodeException.INVALID_PARAMETERS;

        final ErrorObjectDto errorObjectDto = new ErrorObjectDto();
        errorObjectDto.code(codeException.name());
        errorObjectDto.message(userUseCaseException.getMessage().split(":")[0]);

        final HttpStatus httpStatus = HTTP_STATUS_BY_CODE_EXCEPTION.getOrDefault(codeException, HttpStatus.NOT_EXTENDED);
        return new ResponseEntity<>(errorObjectDto, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObjectDto> handlerException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {
        final CodeException codeException = CodeException.INVALID_PARAMETERS;
        final FieldError fieldError = methodArgumentNotValidException.getFieldErrors().getFirst();

        final ErrorObjectDto errorObjectDto = new ErrorObjectDto();
        errorObjectDto.code(codeException.name());
        errorObjectDto.message(String.format(codeException.getMessageFormat(), fieldError.getField()));

        final HttpStatus httpStatus = HTTP_STATUS_BY_CODE_EXCEPTION.getOrDefault(codeException, HttpStatus.NOT_EXTENDED);

        return new ResponseEntity<>(errorObjectDto, httpStatus);
    }
}
