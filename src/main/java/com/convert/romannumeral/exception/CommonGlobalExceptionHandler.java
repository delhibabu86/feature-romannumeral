package com.convert.romannumeral.exception;

import com.convert.romannumeral.model.ErrorMessage;
import com.convert.romannumeral.model.ErrorResponse;
import com.convert.romannumeral.service.IntegerToRomanNumberServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Global Exception class to handle spring boot application exceptions
 *
 * @author dvengambhanumoorthy
 */
@RestControllerAdvice
public class CommonGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonGlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String error = exception.getParameterName() + ErrorMessage.MISSING_PARAMETER.getMessage();
        final ErrorResponse errorResponse = this.createError(HttpStatus.BAD_REQUEST, exception.getMessage(), ErrorMessage.MISSING_PARAMETER.name(), error);
        LOGGER.error(errorResponse.toString());
        return new ResponseEntity(errorResponse, new HttpHeaders(), errorResponse.getStatusDescription());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleConstraintViolationException(final ConstraintViolationException exception) {
        final ErrorResponse errorResponse = this.createError(HttpStatus.BAD_REQUEST, exception.getMessage(), ErrorMessage.INVALID_NUMBER_RANGE.name(), ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        LOGGER.error(errorResponse.toString());
        return errorResponse;
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final ErrorResponse errorResponse = this.createError(HttpStatus.BAD_REQUEST, exception.getMessage(), ErrorMessage.INPUT_TYPE_MISMATCH.name(), ErrorMessage.INPUT_TYPE_MISMATCH.getMessage());
        LOGGER.error(errorResponse.toString());
        return new ResponseEntity(errorResponse, new HttpHeaders(), errorResponse.getStatusDescription());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final Exception exception, final HttpServletRequest request) {
        final ErrorResponse errorResponse = this.createError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), ErrorMessage.INTERNAL_SERVER_ERROR.name(), ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity(errorResponse, new HttpHeaders(), errorResponse.getStatusDescription());
    }

    private ErrorResponse createError(final HttpStatus status, final String localizedMessage, final String errorCode, final String error) {
        return new ErrorResponse(status, localizedMessage, errorCode, error);
    }
}
