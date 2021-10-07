package com.convert.romannumeral.exception;

/**
 * @author dvengambhanumoorthy
 */
public class UserInputQueryParamMissingException extends RuntimeException {

    private String parameterName;
    private String parameterType;

    public UserInputQueryParamMissingException(final String parameterName) {
        this.parameterName = parameterName;
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
