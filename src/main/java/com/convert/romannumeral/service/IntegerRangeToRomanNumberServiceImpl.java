package com.convert.romannumeral.service;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.validator.NumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Class to accept min & max Integer number & convert them into roman numerals via multithreading
 *
 * @author dvengambhanumoorthy
 */
@Service
public class IntegerRangeToRomanNumberServiceImpl implements IntegerRangeToRomanNumberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerRangeToRomanNumberServiceImpl.class);
    private final NumberValidator numberValidator;
    private final IntegerToRomanNumberService integerToRomanNumberService;

    public IntegerRangeToRomanNumberServiceImpl(final NumberValidator numberValidator, final IntegerToRomanNumberService integerToRomanNumberService) {
        this.numberValidator = numberValidator;
        this.integerToRomanNumberService = integerToRomanNumberService;
    }


    /**
     * Accepts min & max range,creates parallel thread for each number
     * joins each individual response & sorts the final outcome by Input number ASC
     * Sort is needed as each parallel thread completion could be in random order
     *
     * @param min
     * @param max
     * @return Map<String, Object>
     * throws : CompletionException – if this future completed exceptionally or a completion computation threw an exception
     */
    public Map<String, Object> convertIntegerRangeToRomanNumber(final Integer min, final Integer max) {
        LOGGER.debug(" Entering Method  convertIntegerRangeToRomanNumber in service ");

        //Validates min & max values
        this.numberValidator.validate(min, max);

        final long startTime = System.currentTimeMillis();

        List<CompletableFuture<IntegerToRomanResponse>> completableFuturesList = new ArrayList<>(max - min + 1);
        for (int start = min; start <= max; start++) {
            completableFuturesList.add(this.integerToRomanNumberService.convertAsyncIntegerToRomanNumber(start));
        }

        final CompletableFuture<Void> allOfCompletableFuture = CompletableFuture.allOf(completableFuturesList.toArray(new CompletableFuture[0]));
        final CompletableFuture<List<IntegerToRomanResponse>> listCompletableFuture = allOfCompletableFuture.thenApply(result ->
                completableFuturesList.stream().map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        List<IntegerToRomanResponse> integerToRomanResponseList = listCompletableFuture.join();
        Collections.sort(integerToRomanResponseList);
        final long endTime = System.currentTimeMillis();

        LOGGER.info(" Total Time taken for convertIntegerRangeToRomanNumber in IntegerToRomanNumberServiceImpl  is ----> {} {}", (endTime - startTime), "ms");
        LOGGER.debug(" Exiting Method  convertIntegerRangeToRomanNumber in service");

        return Map.of("conversions", integerToRomanResponseList);
    }

}
