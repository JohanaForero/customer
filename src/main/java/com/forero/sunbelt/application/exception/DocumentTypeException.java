package com.forero.sunbelt.application.exception;

import com.forero.sunbelt.domain.exception.CodeException;

public class DocumentTypeException extends CoreException {
    public DocumentTypeException(final CodeException codeException, final Exception exception, final String... fields) {
        super(codeException, exception, fields);
    }
}
