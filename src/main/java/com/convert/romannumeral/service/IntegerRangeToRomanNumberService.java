package com.convert.romannumeral.service;

import java.util.Map;

/**
 * @author dvengambhanumoorthy
 */
public interface IntegerRangeToRomanNumberService {
    Map<String,Object> convertIntegerRangeToRomanNumber(Integer min, Integer max);
}
