package com.convert.romannumeral.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Contains business logic to convert integer to roman numeral
 *
 * @author dvengambhanumoorthy
 */
@Service
public class IntegerToRomanNumberServiceImpl implements IntegerToRomanNumberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerToRomanNumberServiceImpl.class);

    @Override
    public void convertIntegerToRomanNumber(int number) {
        final long startTime = System.currentTimeMillis();
        LOGGER.info(" Entering Method  convertIntegerToRomanNumber in class ----> {} ", IntegerToRomanNumberServiceImpl.class);
        LOGGER.info(" Exiting Method  convertIntegerToRomanNumber in class ----> {} ", IntegerToRomanNumberServiceImpl.class);
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total Time taken for convertIntegerToRomanNumber in IntegerToRomanNumberServiceImpl  is ----> {} ,{}", (endTime - startTime), "ms");
    }
}
