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
import java.util.Map;

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
    private final IntegerToRomanNumberService integerToRomanNumberService;

    public IntegerToRomanNumberController(final IntegerToRomanNumberService integerToRomanNumberService) {
        this.integerToRomanNumberService = integerToRomanNumberService;
    }

    /**
     * @param number        Integer number entered by user to identify corresponding roman numeral
     * @param minimumNumber Minimum range param entered by user to identify corresponding roman numeral
     * @param maxNumber     Maximum range param entered by user to identify corresponding roman numeral
     * @return Map<String, Object> Returns given input & calculated roman numeral
     */
    @GetMapping()
    @ApiOperation("Convert Integer to Roman Numeral")
    //TODO how to have enum & http status value configured below
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Map<String, Object> convertIntegerToRomanNumber(@RequestParam(value = "query", required = false)
                                                           @Min(value = 1)
                                                           @Max(value = 3999)
                                                                   Integer number,
                                                           @RequestParam(value = "min", required = false)
                                                           @Min(value = 1)
                                                           @Max(value = 3998)
                                                                   Integer minimumNumber,
                                                           @RequestParam(value = "max", required = false)
                                                           @Max(value = 3999)
                                                                   Integer maxNumber) {
        final long startTime = System.currentTimeMillis();
        LOGGER.info(" Incoming Request ----> {} ", number);
        final Map<String, Object> response = this.integerToRomanNumberService.convertIntegerToRomanNumber(number);
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total Time taken for GET API /romannumeral is ----> {} ,{}", (endTime - startTime), "ms");
        return response;
    }

}
