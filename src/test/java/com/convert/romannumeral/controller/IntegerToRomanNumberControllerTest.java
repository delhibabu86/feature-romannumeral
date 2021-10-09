package com.convert.romannumeral.controller;

import com.convert.romannumeral.service.IntegerToRomanNumberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
@Disabled
class IntegerToRomanNumberControllerTest {
    @InjectMocks
    IntegerToRomanNumberController integerToRomanNumberController;
    @Mock
    IntegerToRomanNumberService integerToRomanNumberService;

   /* @Test
    void convertIntegerToRomanNumber_missingServletRequestParameter() {
        assertThrows(MissingServletRequestParameterException.class, () -> this.integerToRomanNumberController.convertIntegerToRomanNumber(-1, -2, -3));

    }

    @Test
    void convertIntegerToRomanNumber_typeMisMatch() {
        assertThrows(TypeMismatchException.class, () -> this.integerToRomanNumberController.convertIntegerToRomanNumber(-1, -2, -3));

    }

    @Test
    void convertIntegerToRomanNumber_constraintViolation() {
        doNothing().when(this.integerToRomanNumberService).convertIntegerToRomanNumber(ArgumentMatchers.anyInt());
        final ConstraintViolationException constraintViolationException = assertThrows(ConstraintViolationException.class, () -> this.integerToRomanNumberController.convertIntegerToRomanNumber(-1, -2, -3));
    }*/

   /* @Test
    void convertIntegerToRomanNumber_queryParam_present_success() {
        when(this.integerToRomanNumberService.convertIntegerToRomanNumber(ArgumentMatchers.anyInt())).thenReturn(null);
        final NumberRangeToRomanResponse numberRangeToRomanResponse = this.integerToRomanNumberController.convertIntegerToRomanNumber(10, null, null);
        Assertions.assertNull(numberRangeToRomanResponse);
    }*/

}
