package com.convert.romannumeral.controller;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.dto.NumberRangeToRomanResponse;
import com.convert.romannumeral.model.ErrorResponse;
import com.convert.romannumeral.service.IntegerToRomanNumberService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param query Integer number entered by user to identify corresponding roman numeral
     * @param min   Minimum range param entered by user to identify corresponding roman numeral
     * @param max   Maximum range param entered by user to identify corresponding roman numeral
     * @return Map<String, Object> Returns given input & calculated roman numeral
     */
    @GetMapping()
    @ApiOperation(value = "Convert Integer to Roman Numeral",
            notes = "Returns roman numeral for given integer number range [1-3999]")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request - Invalid Number range", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    public NumberRangeToRomanResponse convertIntegerToRomanNumber(
            @ApiParam(name = "query", type = "Integer", value = "Integer range [1-3999]")
            @RequestParam(value = "query", required = false) Integer query,
            @ApiParam(name = "max", type = "Integer", value = "greater than min.Integer range [1-3999]")
            @RequestParam(value = "max", required = false) Integer max,
            @ApiParam(name = "min", type = "Integer", value = "less than max.Integer range [1-3999]")
            @RequestParam(value = "min", required = false) Integer min) {
        final long startTime = System.currentTimeMillis();
        LOGGER.info(" Incoming Request ----> {} ", query);
        if (ObjectUtils.isNotEmpty(query)) {
            final IntegerToRomanResponse response = this.integerToRomanNumberService.convertIntegerToRomanNumber(query);
            final long endTime = System.currentTimeMillis();
            LOGGER.info(" Total time taken for GET API /romannumeral with query param is ----> {} {}", (endTime - startTime), "ms");
            return null;
            // return response;
        }

        final NumberRangeToRomanResponse response = this.integerToRomanNumberService.convertIntegerRangeToRomanNumber(min, max);
        final long endTime = System.currentTimeMillis();
        LOGGER.info(" Total time taken for GET API /romannumeral with range param is ----> {} {}", (endTime - startTime), "ms");
        return response;

    }

}
