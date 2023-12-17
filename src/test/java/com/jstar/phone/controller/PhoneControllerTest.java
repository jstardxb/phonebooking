package com.jstar.phone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jstar.phone.dto.BookPhoneRequest;
import com.jstar.phone.dto.ReturnPhoneRequest;
import com.jstar.phone.entities.Phone;
import com.jstar.phone.exception.GlobalExceptionHandler;
import com.jstar.phone.exception.PhoneNotFoundException;
import com.jstar.phone.service.PhoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PhoneControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PhoneService phoneService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private PhoneController mobilePhoneController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(mobilePhoneController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldBookPhone() throws Exception {
        var request = new BookPhoneRequest("Oneplus 9", "User1");
        var expectedResponse = new Phone(1L, "Oneplus 9", LocalDateTime.now(), "User1");

        when(phoneService.bookPhone(anyString(), anyString()))
                .thenReturn(expectedResponse);

        mockMvc.perform(post("/api/phone/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Oneplus 9"))
                .andExpect(jsonPath("$.bookedBy").value("User1"));

        verify(phoneService).bookPhone(anyString(), anyString());
    }

    @Test
    void shouldThrowPhoneNotFoundExceptionWhenBookingUnknownPhone() throws Exception {
        var request = new BookPhoneRequest("Unknown Model", "User1");

        when(phoneService.bookPhone(anyString(), anyString()))
                .thenThrow(PhoneNotFoundException.class);

        mockMvc.perform(post("/api/phone/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PhoneNotFoundException));

        verify(phoneService).bookPhone(anyString(), anyString());
    }

    @Test
    void shouldReturnPhone() throws Exception {
        var request = new ReturnPhoneRequest("Oneplus 9");
        var expectedResponse = new Phone(1L, "Oneplus 9", null, null);

        when(phoneService.returnPhone(anyString()))
                .thenReturn(expectedResponse);

        mockMvc.perform(post("/api/phone/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Oneplus 9"))
                .andExpect(jsonPath("$.bookedAt").doesNotExist());

        verify(phoneService).returnPhone(anyString());
    }
}