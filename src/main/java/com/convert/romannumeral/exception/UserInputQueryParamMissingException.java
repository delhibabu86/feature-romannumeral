package com.convert.romannumeral.exception;

/**
 * @author dvengambhanumoorthy
 */
public class UserInputQueryParamMissingException extends RuntimeException {

    private final String parameterName;

    public UserInputQueryParamMissingException(final String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

}
