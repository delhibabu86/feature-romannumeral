package com.convert.romannumeral.service;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.validator.NumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Unit Tests the Service mocking number validator class
 *
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
public class IntegerToRomanNumberServiceImplTest {
    @InjectMocks
    IntegerToRomanNumberServiceImpl integerToRomanNumberService;
    @Mock
    NumberValidator numberValidator;

    @Test
    void test_convertIntegerToRomanNumber_success() {
        final IntegerToRomanResponse expected = new IntegerToRomanResponse("10", "X");
        final IntegerToRomanResponse actual = this.integerToRomanNumberService.convertIntegerToRomanNumber(10);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getInput(), actual.getInput());
        Assertions.assertEquals(expected.getOutput(), actual.getOutput());

    }

    @Test
    void test_convertAsyncIntegerToRomanNumber_success() throws ExecutionException, InterruptedException {
        final IntegerToRomanResponse expected = new IntegerToRomanResponse("10", "X");
        final CompletableFuture<IntegerToRomanResponse> actual = this.integerToRomanNumberService.convertAsyncIntegerToRomanNumber(10);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getInput(), actual.get().getInput());
        Assertions.assertEquals(expected.getOutput(), actual.get().getOutput());

    }
}
