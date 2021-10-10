package com.convert.romannumeral.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author dvengambhanumoorthy
 */
public class IntegerToRomanResponse implements Comparable<IntegerToRomanResponse>,Serializable {
    @Serial
    private static final long serialVersionUID = -1849589829774299452L;
    private final String input;
    private final String output;

    public IntegerToRomanResponse() {
        super();
        this.input = null;
        this.output = null;
    }

    public IntegerToRomanResponse(final String input, final String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int compareTo(final IntegerToRomanResponse o) {
        return (Integer.valueOf(this.input)).compareTo(Integer.valueOf(o.input));
    }
}
