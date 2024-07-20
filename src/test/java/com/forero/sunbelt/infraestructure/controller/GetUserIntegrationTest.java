package com.forero.sunbelt.infraestructure.controller;

import com.forero.sunbelt.openapi.api.model.ErrorObjectDto;
import com.forero.sunbelt.openapi.api.model.UserRequestDto;
import com.forero.sunbelt.openapi.api.model.UserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.stream.Stream;

class GetUserIntegrationTest extends BaseIT {
    private static final String BASE_PATH = "/sunbelt/customer";

    private static Stream<Arguments> getRequestInvalidReturnInvalidParameters() {
        return Stream.of(
                Arguments.of("request with documentType null return BadRequest", "10121314", null, "documentType"),
                Arguments.of("request with documentNumber null return BadRequest", null, "c", "documentNumber")
        );
    }

    @Test
    void test_withRequestValid_ShouldReturnStatusOkAndUserResponseDto() throws Exception {
        //Given
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.documentNumber("10121314");
        userRequestDto.documentType("C");
        UserResponseDto expected = new UserResponseDto();
        expected.setAddress("Calle Falsa 123");
        expected.setCity("Bogota");
        expected.setSurname("Perez");
        expected.setFirstName("Juan");
        expected.setSecondName("Carlos");
        expected.setPhone("1234567890");
        expected.setSecondSurname("Gomez");

        //When
        final ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.post(URI.create(BASE_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsBytes(userRequestDto)));

        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk());
        final String body = response.andReturn().getResponse().getContentAsString();
        final UserResponseDto actual = OBJECT_MAPPER.readValue(body, UserResponseDto.class);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getRequestInvalidReturnInvalidParameters")
    void test_withRequestInvalid_ShouldReturnExceptionInvalidParameters(final String testName,
                                                                        final String documentNumber,
                                                                        final String documentType,
                                                                        final String field) throws Exception {
        //Given
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.documentNumber(documentNumber);
        userRequestDto.documentType(documentType);

        final ErrorObjectDto expected = new ErrorObjectDto();
        expected.code("INVALID_PARAMETERS");
        expected.message(String.format("Invalid %s parameters", field));

        //When
        final ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.post(URI.create(BASE_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsBytes(userRequestDto)));

        //Then
        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        final String body = response.andReturn().getResponse().getContentAsString();
        final ErrorObjectDto actual = OBJECT_MAPPER.readValue(body, ErrorObjectDto.class);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_withRequestWithDocumentNumberInvalid_ShouldReturnExceptionCustomerNotFound() throws Exception {
        //Given
        final UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.documentNumber("10");
        userRequestDto.documentType("C");

        final ErrorObjectDto expect = new ErrorObjectDto();
        expect.code("CUSTOMER_NOT_FOUND");
        expect.message("Customer not found");

        //When
        final ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.post(URI.create(BASE_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsBytes(userRequestDto)));

        //Then
        response.andExpect(MockMvcResultMatchers.status().isNotFound());
        final String body = response.andReturn().getResponse().getContentAsString();
        final ErrorObjectDto actual = OBJECT_MAPPER.readValue(body, ErrorObjectDto.class);
        Assertions.assertEquals(expect, actual);
    }
}
