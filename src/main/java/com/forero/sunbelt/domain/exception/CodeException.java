package com.forero.sunbelt.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeException {
    INTERNAL_SERVER_ERROR("Internal server error"),
    CUSTOMER_NOT_FOUND("Customer not found"),
    INVALID_PARAMETERS("Invalid %s parameters"),
    INVALID_TYPE_DOCUMENT("Only 'C' (cedula) and 'P' (passport) are accepted.");

    private final String messageFormat;
}
