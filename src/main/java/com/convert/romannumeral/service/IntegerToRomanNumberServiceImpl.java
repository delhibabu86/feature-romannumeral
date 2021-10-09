package com.convert.romannumeral.service;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.validator.NumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Contains business logic to convert integer to roman numeral
 *
 * @author dvengambhanumoorthy
 */
@Service
public class IntegerToRomanNumberServiceImpl implements IntegerToRomanNumberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerToRomanNumberServiceImpl.class);
    private static final int[] integerNumbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] romanNumbers = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private final NumberValidator numberValidator;

    public IntegerToRomanNumberServiceImpl(final NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }


    /**
     * Logic to convert integer number into roman number
     * Time Complexity - O(1)
     * Space Complexity - O(1)
     *
     * @param number- Integer number entered by user to identify corresponding roman numeral
     * @return IntegerToRomanResponse Returns given input & calculated roman numeral
     */
    @Override
    public IntegerToRomanResponse convertIntegerToRomanNumber(final Integer number) {
        LOGGER.info(" Entering Method  convertIntegerToRomanNumber in service ");
        this.numberValidator.validate(number);
        final long startTime = System.currentTimeMillis();
        final StringBuilder romanLiterals = new StringBuilder();
        int value = number;
        for (int num = 0; num < integerNumbers.length; num++) {
            while (value >= integerNumbers[num]) {
                value -= integerNumbers[num];
                romanLiterals.append(romanNumbers[num]);
            }
        }
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total Time taken for convertIntegerToRomanNumber in IntegerToRomanNumberServiceImpl  is ----> {} ,{}", (endTime - startTime), "ms");
        LOGGER.info(" Exiting Method  convertIntegerToRomanNumber in service");
        return new IntegerToRomanResponse(String.valueOf(number), romanLiterals.toString());
    }

    @Override
    @Async
    public CompletableFuture<IntegerToRomanResponse> convertAsyncIntegerToRomanNumber(final Integer number) {
        return CompletableFuture.completedFuture(this.convertIntegerToRomanNumber(number));
    }


}
