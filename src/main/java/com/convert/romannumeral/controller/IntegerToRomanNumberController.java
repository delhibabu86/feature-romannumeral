package com.convert.romannumeral.controller;

import com.convert.romannumeral.service.IntegerToRomanNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Spring Rest Controller - convert Integer to Roman Numeral
 * Design Principle - Single Responsibility Principle
 *
 * @author dvengambhanumoorthy
 */
@RestController
@RequestMapping(value = "/romannumeral", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api("Integer to Roman Numeral API")
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
    @ApiOperation("Convert Integer to Roman Numeral")
    //TODO how to have enum & http status value configured below
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void convertIntegerToRomanNumber(@RequestParam("query")
                                            @Min(value = 1)
                                            @Max(value = 3999)
                                                    Integer number,
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
