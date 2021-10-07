package com.convert.romannumeral.enums;

/**
 * @author dvengambhanumoorthy
 */
public enum ErrorMessage {
    INVALID_NUMBER_RANGE("Invalid number range , please enter an integer number between 1 to 3999"),
    MISSING_PARAMETER(" parameter is missing "),
    INPUT_TYPE_MISMATCH("Invalid input type provided, please enter an integer number between 1 to 3999"),
    INPUT_MISSING_ATLEAST_ONE_FIELD("Please enter either query or min & max parameter"),
    INTERNAL_SERVER_ERROR("Internal Server Error");
    private String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
