package com.convert.romannumeral.exception;

/**
 * @author dvengambhanumoorthy
 */
public class UserInputQueryParamMissingException extends RuntimeException {

    private final String parameterName;
    private final String parameterType;

    public UserInputQueryParamMissingException(final String parameterName) {
        this.parameterName = parameterName;
        this.parameterType = null;
    }

    public UserInputQueryParamMissingException(final String parameterName, final String parameterType) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getParameterType() {
        return parameterType;
    }
}
