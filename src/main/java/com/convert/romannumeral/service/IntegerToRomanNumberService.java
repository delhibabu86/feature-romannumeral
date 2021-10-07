package com.convert.romannumeral.service;

import java.util.Map;

/**
 * @author dvengambhanumoorthy
 */
public interface IntegerToRomanNumberService {
    Map<String, Object> convertIntegerToRomanNumber(Integer number);

    Map<String, Object> convertIntegerRangeToRomanNumber(Integer min, Integer max);
}
