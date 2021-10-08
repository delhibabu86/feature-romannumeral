package com.convert.romannumeral.exception;

import com.convert.romannumeral.enums.ErrorMessage;

/**
 * @author dvengambhanumoorthy
 */
public class UserInputException extends RuntimeException {
    private final ErrorMessage errorMessage;
    private final String errorField;

    public UserInputException(final String errorField, final ErrorMessage errorMessage) {
        this.errorField = errorField;
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public String getErrorField() {
        return errorField;
    }
}
