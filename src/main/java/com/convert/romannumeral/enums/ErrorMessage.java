package com.convert.romannumeral.enums;

/**
 * @author dvengambhanumoorthy
 */
public enum ErrorMessage {
    INVALID_NUMBER_RANGE("Invalid number range , please enter an integer number between 1 to 3999"),
    MISSING_PARAMETER(" parameter is missing "),
    INPUT_TYPE_MISMATCH("Invalid input type provided, please enter an integer number between 1 to 3999"),
    MIN_MAX_VALUE_INVALID("Min should be smaller than max number.Please enter an integer number between 1 to 3999"),
    INVALID_INPUT_PROVIDED("Please enter either query or (min & max) but not both"),
    INTERNAL_SERVER_ERROR("Internal Server Error");
    private String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
