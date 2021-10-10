package com.convert.romannumeral.validator;

import com.convert.romannumeral.exception.UserInputException;
import com.convert.romannumeral.exception.UserInputInvalidRangeException;
import com.convert.romannumeral.exception.UserInputQueryParamMissingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit Tests the NumberValidator with business scenarios
 *
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
class NumberValidatorTest {
    @InjectMocks
    NumberValidator numberValidator;

    @Test
    void test_validateQueryField_success() {
        this.numberValidator.validate(10);
    }

    @Test
    void test_validateQueryField_InvalidRange_zero() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(0));
    }

    @Test
    void test_validateQueryField_InvalidRange_4000() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(4000));
    }

    @Test
    void test_validateQueryField_InvalidRange_negative() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(-6566));
    }

    @Test
    void test_validateRangeField_present_minAndMaxInvalid() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(0, 4500));
    }

    @Test
    void test_validateRangeField_present_minInvalidOuterBoundary_MaxValid() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(43333, 3899));
    }

    @Test
    void test_validateRangeField_present_minInvalid_MaxValid() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(-22, 3899));
    }

    @Test
    void test_validateRangeField_present_minValid_maxInvalidInnerBoundary() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(23, -323));
    }

    @Test
    void test_validateRangeField_present_minValid_MaxInvalid() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(22, 76767));
    }

    @Test
    void test_validateRangeField_minEqualToMax() {
        Assertions.assertThrows(UserInputInvalidRangeException.class, () -> this.numberValidator.validate(1, 1));
    }

    @Test
    void test_validateRangeField_minGreaterThanMax() {
        Assertions.assertThrows(UserInputInvalidRangeException.class, () -> this.numberValidator.validate(10, 1));
    }

    @Test
    void test_validateRangeField_success() {
        this.numberValidator.validate(10, 11);
    }

    @Test
    void test_validate_QueryPresent_minAndMaxPresent() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(10, 12, 13));
    }

    @Test
    void test_validate_QueryPresent_minPresentAndMaxNotPresent() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(10, 12, null));
    }

    @Test
    void test_validate_QueryPresent_minNotPresentAndMaxPresent() {
        Assertions.assertThrows(UserInputException.class, () -> this.numberValidator.validate(10, null, 12));
    }

    @Test()
    void test_validate_QueryPresent_minAndMaxNotPresent() {
        Assertions.assertDoesNotThrow(() -> this.numberValidator.validate(10, null, null));
    }

    @Test
    void test_validate_QueryNotPresent_minPresentAndMaxNotPresent() {
        Assertions.assertThrows(UserInputQueryParamMissingException.class, () -> this.numberValidator.validate(null, 12, null));
    }

    @Test
    void test_validate_QueryNotPresent_minNotPresentAndMaxPresent() {
        Assertions.assertThrows(UserInputQueryParamMissingException.class, () -> this.numberValidator.validate(null, null, 24));
    }

    @Test
    void test_validate_QueryNotPresent_minAndMax_NotPresent() {
        Assertions.assertThrows(UserInputQueryParamMissingException.class, () -> this.numberValidator.validate(null, null, null));
    }

    @Test
    void test_validate_QueryNotPresent_minAndMax_Present() {
        Assertions.assertDoesNotThrow(() -> this.numberValidator.validate(null, 10, 20));
    }
}
