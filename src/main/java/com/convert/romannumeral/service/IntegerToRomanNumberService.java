package com.convert.romannumeral.service;

import com.convert.dto.IntegerToRomanResponse;
import com.convert.dto.NumberRangeToRomanResponse;

/**
 * @author dvengambhanumoorthy
 */
public interface IntegerToRomanNumberService {
    IntegerToRomanResponse convertIntegerToRomanNumber(Integer number);

    NumberRangeToRomanResponse convertIntegerRangeToRomanNumber(Integer min, Integer max);
}
