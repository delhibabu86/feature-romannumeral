package com.convert.romannumeral.service;

import com.convert.romannumeral.dto.IntegerToRomanResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author dvengambhanumoorthy
 */
public interface IntegerToRomanNumberService {
    IntegerToRomanResponse convertIntegerToRomanNumber(Integer number);
    CompletableFuture<IntegerToRomanResponse> convertAsyncIntegerToRomanNumber(Integer number);
}
