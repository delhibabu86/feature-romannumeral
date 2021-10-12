package com.convert.romannumeral.controller;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.model.ErrorResponse;
import com.convert.romannumeral.service.IntegerRangeToRomanNumberService;
import com.convert.romannumeral.service.IntegerToRomanNumberService;
import com.convert.romannumeral.validator.NumberValidator;
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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Spring Rest Controller - Rest API to convert Integer to Roman Numeral
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
    private final IntegerRangeToRomanNumberService integerRangeToRomanNumberService;
    private final NumberValidator numberValidator;


    public IntegerToRomanNumberController(final IntegerToRomanNumberService integerToRomanNumberService,
                                          final IntegerRangeToRomanNumberService integerRangeToRomanNumberService,
                                          final NumberValidator numberValidator) {
        this.integerToRomanNumberService = integerToRomanNumberService;
        this.integerRangeToRomanNumberService = integerRangeToRomanNumberService;
        this.numberValidator = numberValidator;
    }

    /**
     * Handling 3 scenarios :
     * 1) Only query is searched by user in the query param, then the corresponding roman numeral sent out
     * 2) Only min & max searched by user, corresponding roman numeral shared
     *
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
    public Map<String, Object> convertIntegerToRomanNumber(
            @ApiParam(name = "query", type = "Integer", value = "Integer range [1-3999]")
            @RequestParam(value = "query", required = false) Integer query,
            @ApiParam(name = "max", type = "Integer", value = "greater than min.Integer range [1-3999]")
            @RequestParam(value = "max", required = false) Integer max,
            @ApiParam(name = "min", type = "Integer", value = "less than max.Integer range [1-3999]")
            @RequestParam(value = "min", required = false) Integer min) {

        LOGGER.info(" Incoming Request query min max ----> {} {} {} ", query, min, max);

        this.numberValidator.validate(query, min, max);

        final long startTime = System.currentTimeMillis();
        if (ObjectUtils.isNotEmpty(query)) {

            final IntegerToRomanResponse response = this.integerToRomanNumberService.convertIntegerToRomanNumber(query);

            final Map<String, Object> romanNumberOutput = new LinkedHashMap<>();
            romanNumberOutput.put("input", response.getInput());
            romanNumberOutput.put("output", response.getOutput());

            final long endTime = System.currentTimeMillis();
            LOGGER.info(" Total time taken for GET API /romannumeral with query param is ----> {} {}", (endTime - startTime), "ms");
            return romanNumberOutput;

        } else {
            final Map<String, Object> response = this.integerRangeToRomanNumberService.convertIntegerRangeToRomanNumber(min, max);
            final long endTime = System.currentTimeMillis();

            LOGGER.info(" Total time taken for GET API /romannumeral with range param is ----> {} {}", (endTime - startTime), "ms");
            return response;
        }
    }

}
