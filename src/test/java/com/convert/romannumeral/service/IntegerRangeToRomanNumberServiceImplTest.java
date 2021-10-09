package com.convert.romannumeral.service;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.validator.NumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;

/**
 * Unit Tests the Service mocking number validator class
 *
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
public class IntegerRangeToRomanNumberServiceImplTest {
    @InjectMocks
    IntegerRangeToRomanNumberServiceImpl integerRangeToRomanNumberService;
    @Mock
    NumberValidator numberValidator;
    @Mock
    IntegerToRomanNumberServiceImpl integerToRomanNumberService;

    @Test
    void test_convertIntegerRangeToRomanNumber() {
        IntegerToRomanResponse integerToRomanResponse = new IntegerToRomanResponse("11", "XI");
        IntegerToRomanResponse integerToRomanResponse1 = new IntegerToRomanResponse("11", "XI");
        final CompletableFuture<IntegerToRomanResponse> x1 = CompletableFuture.completedFuture(integerToRomanResponse);
        final CompletableFuture<IntegerToRomanResponse> x2 = CompletableFuture.completedFuture(integerToRomanResponse1);
        when(this.integerToRomanNumberService.convertAsyncIntegerToRomanNumber(ArgumentMatchers.anyInt())).thenReturn(x1);
        when(this.integerToRomanNumberService.convertAsyncIntegerToRomanNumber(ArgumentMatchers.anyInt())).thenReturn(x2);
        final Map<String, Object> actual = this.integerRangeToRomanNumberService.convertIntegerRangeToRomanNumber(1, 2);
        Assertions.assertNotNull(actual);
    }

}
