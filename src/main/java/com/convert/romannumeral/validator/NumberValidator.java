package com.convert.romannumeral.validator;

import com.convert.romannumeral.enums.ErrorMessage;
import com.convert.romannumeral.exception.UserInputException;
import com.convert.romannumeral.exception.UserInputInvalidRangeException;
import com.convert.romannumeral.exception.UserInputQueryParamMissingException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

/**
 * Validator class to handle business scenarios
 *
 * @author dvengambhanumoorthy
 */

@Service
public class NumberValidator {
    /**
     * Use case - If query param entered is empty then throw MissingServletRequestParameterException
     * Use case - If query param entered is not then throw UserInputException
     *
     * @param number
     */
    public void validate(final Integer number) {
        if (number < 1 || number > 3999) {
            throw new UserInputException("query", ErrorMessage.INVALID_NUMBER_RANGE);
        }
    }

    public void validate(final Integer min, final Integer max) {

        if (min < 1 || min > 3999) {
            throw new UserInputException("min", ErrorMessage.INVALID_NUMBER_RANGE);
        }

        if (max < 1 || max > 3999) {
            throw new UserInputException("max", ErrorMessage.INVALID_NUMBER_RANGE);
        }

        if (min >= max) {
            throw new UserInputInvalidRangeException(ErrorMessage.MIN_MAX_VALUE_INVALID);
        }
    }


    public void validate(final Integer query, final Integer min, final Integer max) {
        try {

            if (ObjectUtils.isNotEmpty(query)) {
                if (ObjectUtils.isNotEmpty(min) || ObjectUtils.isNotEmpty(max)) {
                    throw new UserInputException(ErrorMessage.INVALID_INPUT_PROVIDED);
                }
            } else {
                if (ObjectUtils.isEmpty(min) && ObjectUtils.isEmpty(max)) {
                    throw new MissingServletRequestParameterException("Both min & max", "Integer");
                }
                if (ObjectUtils.isEmpty(min) || ObjectUtils.isEmpty(max)) {
                    throw new MissingServletRequestParameterException(ObjectUtils.isEmpty(min) ? "min" : "max", "Integer");
                }
            }
        } catch (MissingServletRequestParameterException exception) {
            throw new UserInputQueryParamMissingException(exception.getParameterName());
        }

    }

}
