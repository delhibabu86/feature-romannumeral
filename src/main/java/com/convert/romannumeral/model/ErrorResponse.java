package com.convert.romannumeral.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author dvengambhanumoorthy
 */
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -5402974223370441730L;
    private Integer statusCode;
    private HttpStatus statusDescription;
    private String errorCode;
    private List<String> errors;
    private String message;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(final HttpStatus httpStatus, final String message, final String errorCode, final String error) {
        this.statusDescription = httpStatus;
        this.message = message;
        errors = Arrays.asList(error);
        this.statusCode = httpStatus.value();
        this.errorCode = errorCode;
    }

    public HttpStatus getStatusDescription() {
        return statusDescription;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
