package com.convert.romannumeral.service;

import com.convert.romannumeral.validator.NumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Contains business logic to convert integer to roman numeral
 *
 * @author dvengambhanumoorthy
 */
@Service
public class IntegerToRomanNumberServiceImpl implements IntegerToRomanNumberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerToRomanNumberServiceImpl.class);
    private static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
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
     * @return Map<String, Object> Returns given input & calculated roman numeral
     */
    @Override
    public Map<String, Object> convertIntegerToRomanNumber(final Integer number) {
        LOGGER.info(" Entering Method  convertIntegerToRomanNumber in service ");
        this.numberValidator.validate(number);
        final long startTime = System.currentTimeMillis();
        final StringBuilder romanLiterals = new StringBuilder();
        int value = number;
        for (int i = 0; i < numbers.length; i++) {
            while (value >= numbers[i]) {
                value -= numbers[i];
                romanLiterals.append(romanNumbers[i]);
            }
        }
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total Time taken for convertIntegerToRomanNumber in IntegerToRomanNumberServiceImpl  is ----> {} ,{}", (endTime - startTime), "ms");
        LOGGER.info(" Exiting Method  convertIntegerToRomanNumber in service");
        return Map.of("input", String.valueOf(number), "output", romanLiterals.toString());
    }

    @Override
    public Map<String, Object> convertIntegerRangeToRomanNumber(final Integer min, final Integer max) {
        LOGGER.info(" Entering Method  convertIntegerRangeToRomanNumber in service ");
        this.numberValidator.validate(min, max);
        final long startTime = System.currentTimeMillis();
        //TODO logic
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total Time taken for convertIntegerRangeToRomanNumber in IntegerToRomanNumberServiceImpl  is ----> {} ,{}", (endTime - startTime), "ms");
        LOGGER.info(" Exiting Method  convertIntegerRangeToRomanNumber in service");
        return null;
    }
}
