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
    private Integer code;
    private String errorCode;
    private List<String> errors;
    private String message;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(final HttpStatus httpStatus, final String message, final String errorCode, final String error) {
        this.code = httpStatus.value();
        this.message = message;
        errors = Arrays.asList(error);
        this.errorCode = errorCode;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
