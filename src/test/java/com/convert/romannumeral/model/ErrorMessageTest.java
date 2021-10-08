package com.convert.romannumeral.model;

import com.convert.romannumeral.enums.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * @author dvengambhanumoorthy
 */
class ErrorMessageTest {

    @Test
    void test_errorMessage() {
        Assertions.assertNotNull(ErrorMessage.INVALID_NUMBER_RANGE);
        Assertions.assertNotNull(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        Assertions.assertNotNull(ErrorMessage.MISSING_PARAMETER);
        Assertions.assertNotNull(ErrorMessage.MISSING_PARAMETER.getMessage());
        Assertions.assertNotNull(ErrorMessage.INPUT_TYPE_MISMATCH);
        Assertions.assertNotNull(ErrorMessage.INPUT_TYPE_MISMATCH.getMessage());
        Assertions.assertNotNull(ErrorMessage.INTERNAL_SERVER_ERROR);
        Assertions.assertNotNull(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
    }
}
