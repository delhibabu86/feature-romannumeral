package com.convert.romannumeral.exception;

import com.convert.romannumeral.enums.ErrorMessage;

/**
 * @author dvengambhanumoorthy
 */
public class UserInputInvalidRangeException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public UserInputInvalidRangeException(final ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
