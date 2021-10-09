package com.convert.romannumeral.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dvengambhanumoorthy
 */
public final class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils(){

    }
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .registerModule(new JavaTimeModule());

    public static <T> T readValue(final String jsonString, final Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(StringUtils.defaultIfBlank(jsonString, StringUtils.EMPTY), type);
        } catch (final Exception exception) {
            LOGGER.error(exception.getMessage());
            return null;
        }
    }

}
