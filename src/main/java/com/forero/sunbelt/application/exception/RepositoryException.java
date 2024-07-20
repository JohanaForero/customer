package com.forero.sunbelt.application.exception;

import com.forero.sunbelt.domain.exception.CodeException;

public class RepositoryException extends CoreException {
    public RepositoryException(final CodeException codeException, final Exception exception, final String... fields) {
        super(codeException, exception, fields);
    }
}
