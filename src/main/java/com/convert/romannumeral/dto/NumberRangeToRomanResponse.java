package com.convert.romannumeral.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dvengambhanumoorthy
 */
public class NumberRangeToRomanResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 4731538189147399630L;

    private final List<IntegerToRomanResponse> conversions;

    public NumberRangeToRomanResponse() {
        super();
        this.conversions = new ArrayList<>();
    }

    public List<IntegerToRomanResponse> getConversions() {
        return conversions;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
