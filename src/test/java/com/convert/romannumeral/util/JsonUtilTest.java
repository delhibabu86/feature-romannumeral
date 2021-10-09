package com.convert.romannumeral.util;

import com.convert.romannumeral.model.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit Tests the JSonUtil
 *
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
class JsonUtilTest {
    @InjectMocks
    JsonUtils jsonUtils;

    @Test
    void test_readValue() {
        final String mockedResponse = "{\"statusCode\":400,\"statusDescription\":\"BAD_REQUEST\",\"errorCode\":\"INVALID_NUMBER_RANGE\",\"errors\":[\"max  - Invalid number range , please enter an integer number between 1 to 3999\"]}";
        final ErrorResponse errorResponse = JsonUtils.readValue(mockedResponse, ErrorResponse.class);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(400, errorResponse.getStatusCode());
        Assertions.assertEquals("BAD_REQUEST", errorResponse.getStatusDescription().name());
        Assertions.assertEquals("INVALID_NUMBER_RANGE", errorResponse.getErrorCode());
        Assertions.assertEquals(1, errorResponse.getErrors().size());
    }

    @Test
    void test_readValue_jsonMappingException() {
        final String mockedResponse = "{\"statusField\":400,\"statusDescription\":\"BAD_REQUEST\",\"errorCode\":\"INVALID_NUMBER_RANGE\",\"errors\":[\"max  - Invalid number range , please enter an integer number between 1 to 3999\"]}";
        Assertions.assertNull(JsonUtils.readValue(mockedResponse, ErrorResponse.class));
    }
}
