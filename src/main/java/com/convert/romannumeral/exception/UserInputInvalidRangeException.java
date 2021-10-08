package com.convert.romannumeral.exception;

import com.convert.romannumeral.enums.ErrorMessage;

/**
 * @author dvengambhanumoorthy
 */
public class UserInputInvalidRangeException extends RuntimeException {
    private final String errorField;
    private final ErrorMessage errorMessage;

    public UserInputInvalidRangeException(final ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
        this.errorField = null;
    }

    public UserInputInvalidRangeException(final String errorField, final ErrorMessage errorMessage) {
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
