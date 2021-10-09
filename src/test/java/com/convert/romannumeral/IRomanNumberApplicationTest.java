package com.convert.romannumeral;

import com.convert.romannumeral.dto.IntegerToRomanResponse;
import com.convert.romannumeral.dto.NumberRangeToRomanResponse;
import com.convert.romannumeral.enums.ErrorMessage;
import com.convert.romannumeral.model.ErrorResponse;
import com.convert.romannumeral.util.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test implemented with Spring Boot
 *
 * @author dvengambhanumoorthy
 */
@SpringBootTest(classes = RomanNumberApplication.class)
@AutoConfigureMockMvc
public class IRomanNumberApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_queryParamAlone_successScenario() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=1234").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        final IntegerToRomanResponse integerToRomanResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), IntegerToRomanResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(integerToRomanResponse);
        Assertions.assertEquals("1234", integerToRomanResponse.getInput());
        Assertions.assertEquals("MCCXXXIV", integerToRomanResponse.getOutput());
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_range_successScenario() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=3212&max=3234").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        final NumberRangeToRomanResponse numberRangeToRomanResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), NumberRangeToRomanResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(numberRangeToRomanResponse);
        Assertions.assertEquals(23,numberRangeToRomanResponse.getConversions().size());
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_missingQueryParams() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.MISSING_PARAMETER.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("Both min & max parameter is missing ", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_queryParamPresent_lessThanAllowableMinValue() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=-2442").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("query  - Invalid number range , please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_queryParamPresent_greaterThanAllowableMinValue() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=7656566").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("query  - Invalid number range , please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_queryParamPresent_withNotAllowableValue() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=sfs4wfw@#$$").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INPUT_TYPE_MISMATCH.name(), errorResponse.getErrorCode());
        Assertions.assertEquals(ErrorMessage.INPUT_TYPE_MISMATCH.getMessage(), errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_onlyMinPresent_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=4").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.MISSING_PARAMETER.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("max parameter is missing ", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_onlyMaxPresent_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?max=4").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.MISSING_PARAMETER.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("min parameter is missing ", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_minAndMaxSame_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=4&max=4").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.MIN_MAX_VALUE_INVALID.name(), errorResponse.getErrorCode());
        Assertions.assertEquals(ErrorMessage.MIN_MAX_VALUE_INVALID.getMessage(), errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_minGreaterThanMax_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=40&max=4").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.MIN_MAX_VALUE_INVALID.name(), errorResponse.getErrorCode());
        Assertions.assertEquals(ErrorMessage.MIN_MAX_VALUE_INVALID.getMessage(), errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_minLessThanRange_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=-1&max=4").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("min  - Invalid number range , please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_maxLessThanRange_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=1&max=-23").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("max  - Invalid number range , please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_minGreaterThanRange_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=47667&max=4").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("min  - Invalid number range , please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_maxGreaterThanRange_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=1&max=565656").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.name(), errorResponse.getErrorCode());
        Assertions.assertEquals("max  - Invalid number range , please enter an integer number between 1 to 3999", errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_minWithCharacters_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=a6d&max=4").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INPUT_TYPE_MISMATCH.name(), errorResponse.getErrorCode());
        Assertions.assertEquals(ErrorMessage.INPUT_TYPE_MISMATCH.getMessage(), errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_maxWithCharacters_QueryParam() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=1&max=j%6").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        final ErrorResponse errorResponse = JsonUtils.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertNotNull(mvcResult);
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusDescription());
        Assertions.assertEquals(ErrorMessage.INPUT_TYPE_MISMATCH.name(), errorResponse.getErrorCode());
        Assertions.assertEquals(ErrorMessage.INPUT_TYPE_MISMATCH.getMessage(), errorResponse.getErrors().get(0));
    }

    @WithMockUser("admin")
    @Test
    void convertIntegerToRomanNumber_generalException() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?min=1&max=69876").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        final String mockedResponse = "{\"statusField\":400,\"statusDescription\":\"BAD_REQUEST\",\"errorCode\":\"INVALID_NUMBER_RANGE\",\"errors\":[\"max  - Invalid number range , please enter an integer number between 1 to 3999\"]}";
        final ErrorResponse errorResponse = JsonUtils.readValue(mockedResponse, ErrorResponse.class);

        Assertions.assertNotNull(mvcResult);
        Assertions.assertNull(errorResponse);
    }
}
