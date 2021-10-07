package com.convert.romannumeral.service;

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

    /**
     * Logic to convert integer number into roman number
     * Time Complexity - O(1)
     * Space Complexity - O(1)
     *
     * @param number- Integer number entered by user to identify corresponding roman numeral
     * @return Map<String, Object> Returns given input & calculated roman numeral
     */
    @Override
    public Map<String, Object> convertIntegerToRomanNumber(final int number) {
        final long startTime = System.currentTimeMillis();
        LOGGER.info(" Entering Method  convertIntegerToRomanNumber in class ----> {} ", IntegerToRomanNumberServiceImpl.class);
        final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] romanNumbers = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        final StringBuilder romanLiterals = new StringBuilder();
        int value = number;
        for (int i = 0; i < numbers.length; i++) {
            while (value >= numbers[i]) {
                value -= numbers[i];
                romanLiterals.append(romanNumbers[i]);
            }
        }

        LOGGER.info(" Exiting Method  convertIntegerToRomanNumber in class ----> {} ", IntegerToRomanNumberServiceImpl.class);
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total Time taken for convertIntegerToRomanNumber in IntegerToRomanNumberServiceImpl  is ----> {} ,{}", (endTime - startTime), "ms");
        return Map.of("input", String.valueOf(number), "output", romanLiterals.toString());
    }
}
