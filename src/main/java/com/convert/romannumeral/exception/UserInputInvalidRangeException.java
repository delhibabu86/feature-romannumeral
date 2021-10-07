package com.convert.romannumeral.exception;

import com.convert.romannumeral.enums.ErrorMessage;

/**
 * @author dvengambhanumoorthy
 */
public class UserInputInvalidRangeException extends RuntimeException {
    private String errorField;
    private ErrorMessage errorMessage;

    public UserInputInvalidRangeException(final ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
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
