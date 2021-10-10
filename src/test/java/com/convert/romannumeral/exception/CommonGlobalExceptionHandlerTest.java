package com.convert.romannumeral.exception;

import com.convert.romannumeral.enums.ErrorMessage;
import com.convert.romannumeral.model.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Unit Tests the global exception handler
 *
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
class CommonGlobalExceptionHandlerTest {
    @InjectMocks
    CommonGlobalExceptionHandler commonGlobalExceptionHandler;
    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    WebRequest webRequest;
    @Mock
    HttpHeaders headers;
    @Mock
    HttpStatus status;
    private final MockHttpServletRequest request = new MockHttpServletRequest();

    @Test
    void test_commonGlobalException() {
        final ResponseEntity<Object> generalException = this.commonGlobalExceptionHandler.handleException(new Exception(), this.request);
        Assertions.assertNotNull(generalException);
        final ErrorResponse errorResponse = (ErrorResponse) generalException.getBody();
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), generalException.getStatusCode().name());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), generalException.getStatusCodeValue());
        Assertions.assertNull(errorResponse.getMessage());
        Assertions.assertEquals("Internal Server Error", errorResponse.getErrors().get(0));
        Assertions.assertEquals(ErrorMessage.INTERNAL_SERVER_ERROR.name(), errorResponse.getErrorCode());
    }

    @Test
    void test_handleUserInputQueryParamMissingException() {
        final ResponseEntity<Object> userInputQueryParamMissingException = this.commonGlobalExceptionHandler.handleUserInputQueryParamMissingException(new UserInputQueryParamMissingException("query"));
        Assertions.assertNotNull(userInputQueryParamMissingException);
        final ErrorResponse errorResponse = (ErrorResponse) userInputQueryParamMissingException.getBody();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), userInputQueryParamMissingException.getStatusCode().name());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), userInputQueryParamMissingException.getStatusCodeValue());
        Assertions.assertNull(errorResponse.getMessage());
        Assertions.assertEquals(ErrorMessage.MISSING_PARAMETER.name(), errorResponse.getErrorCode());
    }

    @Test
    void test_handleUserInputException() {
        final ErrorResponse errorResponse = this.commonGlobalExceptionHandler.handleUserInputException(new UserInputException("query", ErrorMessage.INVALID_NUMBER_RANGE));
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), errorResponse.getStatusDescription().name());
        Assertions.assertNull(errorResponse.getMessage());
        Assertions.assertEquals("query  - Invalid number range , please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
        Assertions.assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.name(), errorResponse.getErrorCode());
    }

    @Test
    void test_handleUserInputException_errorFieldNull() {
        final ErrorResponse errorResponse = this.commonGlobalExceptionHandler.handleUserInputException(new UserInputException(ErrorMessage.INVALID_INPUT_PROVIDED));
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), errorResponse.getStatusDescription().name());
        Assertions.assertNull(errorResponse.getMessage());
        Assertions.assertEquals(ErrorMessage.INVALID_INPUT_PROVIDED.getMessage(), errorResponse.getErrors().get(0));
        Assertions.assertEquals(ErrorMessage.INVALID_INPUT_PROVIDED.name(), errorResponse.getErrorCode());
    }

    @Test
    void test_handleUserInputInvalidRangeException() {
        final ErrorResponse errorResponse = this.commonGlobalExceptionHandler.handleUserInputInvalidRangeException(new UserInputInvalidRangeException(ErrorMessage.MIN_MAX_VALUE_INVALID));
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), errorResponse.getStatusDescription().name());
        Assertions.assertNull(errorResponse.getMessage());
        Assertions.assertEquals(ErrorMessage.MIN_MAX_VALUE_INVALID.getMessage(), errorResponse.getErrors().get(0));
        Assertions.assertEquals(ErrorMessage.MIN_MAX_VALUE_INVALID.name(), errorResponse.getErrorCode());
    }

    @Test
    void test_handleTypeMismatch() {
        final ResponseEntity<Object> generalException = this.commonGlobalExceptionHandler.handleTypeMismatch(new TypeMismatchException("query", Integer.class), headers, status, webRequest);
        Assertions.assertNotNull(generalException);
        final ErrorResponse errorResponse = (ErrorResponse) generalException.getBody();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), generalException.getStatusCode().name());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), generalException.getStatusCodeValue());
        Assertions.assertNotNull(errorResponse.getMessage());
        Assertions.assertEquals("Invalid input type provided, please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
        Assertions.assertEquals(ErrorMessage.INPUT_TYPE_MISMATCH.name(), errorResponse.getErrorCode());
    }


}
