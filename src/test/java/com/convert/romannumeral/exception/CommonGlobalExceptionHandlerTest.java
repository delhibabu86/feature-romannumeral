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
    }

    @Test
    void test_handleUserInputQueryParamMissingException() {
        final ResponseEntity<Object> userInputQueryParamMissingException = this.commonGlobalExceptionHandler.handleUserInputQueryParamMissingException(new UserInputQueryParamMissingException("query"));
        Assertions.assertNotNull(userInputQueryParamMissingException);
    }

    @Test
    void test_handleUserInputException() {
        final ErrorResponse errorResponse = this.commonGlobalExceptionHandler.handleUserInputException(new UserInputException("query", ErrorMessage.INVALID_NUMBER_RANGE));
        Assertions.assertNotNull(errorResponse);
    }

    @Test
    void test_handleUserInputInvalidRangeException() {
        final ErrorResponse errorResponse = this.commonGlobalExceptionHandler.handleUserInputInvalidRangeException(new UserInputInvalidRangeException(ErrorMessage.MIN_MAX_VALUE_INVALID));
        Assertions.assertNotNull(errorResponse);
    }

    @Test
    void test_handleTypeMismatch() {
        final ResponseEntity<Object> generalException = this.commonGlobalExceptionHandler.handleTypeMismatch(new TypeMismatchException("query", Integer.class), headers, status, webRequest);
        Assertions.assertNotNull(generalException);
    }


}
