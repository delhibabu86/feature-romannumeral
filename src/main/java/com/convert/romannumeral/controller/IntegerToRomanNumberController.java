package com.convert.romannumeral.controller;

import com.convert.romannumeral.service.IntegerToRomanNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.awt.*;

/**
 * Spring Rest Controller - convert Integer to Roman Numeral
 * Design Principle - Single Responsibility Principle
 *
 * @author dvengambhanumoorthy
 */
@RestController
@RequestMapping(value = "/romannumeral", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class IntegerToRomanNumberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerToRomanNumberController.class);
    private IntegerToRomanNumberService integerToRomanNumberService;

    public IntegerToRomanNumberController(final IntegerToRomanNumberService integerToRomanNumberService) {
        this.integerToRomanNumberService = integerToRomanNumberService;
    }

    /**
     * API to convert integer to roman number
     * exceptions handled by global exception handler
     *
     * @param number
     */
    @GetMapping()
    public void convertIntegerToRomanNumber(@RequestParam("query")
                                            @Min(value = 1)
                                            @Max(value = 3999)
                                                    int number,
                                            @RequestParam("min")
                                            @Min(value = 1)
                                            @Max(value = 3998)
                                                    int minimumNumber,
                                            @RequestParam("max")
                                            @Max(value = 3999)
                                                    int maxNumber) {
        final long startTime = System.currentTimeMillis();
        LOGGER.info(" Incoming Request ----> {} ", number);
        this.integerToRomanNumberService.convertIntegerToRomanNumber(number);
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total Time taken for GET API /romannumeral is ----> {} ,{}", (endTime - startTime), "ms");
    }

}
