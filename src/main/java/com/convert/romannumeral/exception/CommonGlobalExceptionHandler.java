package com.convert.romannumeral.exception;

import com.convert.romannumeral.enums.ErrorMessage;
import com.convert.romannumeral.model.ErrorResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Global Exception class to handle spring boot application exceptions
 *
 * @author dvengambhanumoorthy
 */
@RestControllerAdvice
public class CommonGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CommonGlobalExceptionHandler.class);

    @ExceptionHandler(UserInputQueryParamMissingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleUserInputQueryParamMissingException(final UserInputQueryParamMissingException exception) {
        final String error = exception.getParameterName() + ErrorMessage.MISSING_PARAMETER.getMessage();
        final ErrorResponse errorResponse = this.createError(HttpStatus.BAD_REQUEST, exception.getMessage(), ErrorMessage.MISSING_PARAMETER.name(), error);
        LOG.error("error in handleUserInputQueryParamMissingException --->: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getCode());
    }

    @ExceptionHandler(UserInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleUserInputException(final UserInputException exception) {
        final ErrorResponse errorResponse = this.createError(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getErrorMessage().name(), buildError(exception));
        LOG.error("error in handleUserInputException --->: {}", errorResponse);
        return errorResponse;
    }

    @ExceptionHandler(UserInputInvalidRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleUserInputInvalidRangeException(final UserInputInvalidRangeException exception) {
        final ErrorResponse errorResponse = this.createError(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getErrorMessage().name(), exception.getErrorMessage().getMessage());
        LOG.error("error in handleUserInputInvalidRangeException --->: {}", errorResponse);
        return errorResponse;
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final ErrorResponse errorResponse = this.createError(HttpStatus.BAD_REQUEST, exception.getMessage(), ErrorMessage.INPUT_TYPE_MISMATCH.name(), ErrorMessage.INPUT_TYPE_MISMATCH.getMessage());
        LOG.error("error in  handleTypeMismatch --->: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final Exception exception, final HttpServletRequest request) {
        final ErrorResponse errorResponse = this.createError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), ErrorMessage.INTERNAL_SERVER_ERROR.name(), ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        LOG.error("error in  handleException --->: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getCode());
    }

    private ErrorResponse createError(final HttpStatus status, final String localizedMessage, final String errorCode, final String error) {
        return new ErrorResponse(status, localizedMessage, errorCode, error);
    }

    private String buildError(final UserInputException exception) {
        final StringBuilder errorBuilder = new StringBuilder();
        if (ObjectUtils.isNotEmpty(exception.getErrorField())) {
            errorBuilder.append(exception.getErrorField())
                    .append(StringUtils.SPACE)
                    .append(" - ")
                    .append(exception.getErrorMessage().getMessage());
        } else {
            errorBuilder.append(exception.getErrorMessage().getMessage());
        }
        return errorBuilder.toString();
    }
}
