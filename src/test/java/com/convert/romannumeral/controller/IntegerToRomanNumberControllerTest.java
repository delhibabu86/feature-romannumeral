package com.convert.romannumeral.controller;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.service.IntegerRangeToRomanNumberService;
import com.convert.romannumeral.service.IntegerToRomanNumberService;
import com.convert.romannumeral.validator.NumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * Unit Tests the controller mocking service layer
 *
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
class IntegerToRomanNumberControllerTest {
    @InjectMocks
    IntegerToRomanNumberController integerToRomanNumberController;
    @Mock
    IntegerToRomanNumberService integerToRomanNumberService;
    @Mock
    IntegerRangeToRomanNumberService integerRangeToRomanNumberService;
    @Mock
    NumberValidator numberValidator;

    @Test
    void convertIntegerToRomanNumber_queryParam_query_present_success() {
        final Map<String, Object> expected = Map.of("input", "10", "output", "X");
        final IntegerToRomanResponse integerToRomanResponse = new IntegerToRomanResponse("10", "X");
        when(this.integerToRomanNumberService.convertIntegerToRomanNumber(ArgumentMatchers.anyInt())).thenReturn(integerToRomanResponse);
        final Map<String, Object> actualResponse = this.integerToRomanNumberController.convertIntegerToRomanNumber(10, null, null);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expected, actualResponse);
    }

    @Test
    void convertIntegerToRomanNumber_queryParam_minAndMax_present_success() {
        final List<IntegerToRomanResponse> integerToRomanResponseList = new ArrayList<>();
        IntegerToRomanResponse response = new IntegerToRomanResponse("10", "X");
        IntegerToRomanResponse response1 = new IntegerToRomanResponse("11", "XI");
        integerToRomanResponseList.add(response);
        integerToRomanResponseList.add(response1);
        final Map<String, Object> expected = Map.of("conversions", integerToRomanResponseList);
        when(this.integerRangeToRomanNumberService.convertIntegerRangeToRomanNumber(
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(Map.of("conversions", integerToRomanResponseList));
        final Map<String, Object> actualResponse = this.integerToRomanNumberController.convertIntegerToRomanNumber(null, 10, 11);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expected, actualResponse);
    }

}
