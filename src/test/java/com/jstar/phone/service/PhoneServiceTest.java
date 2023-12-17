package com.jstar.phone.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @Test
    void shouldBookPhoneSuccessfully() {
        var model = "Oneplus 9";
        var user = "User1";

        var bookedPhone = phoneService.bookPhone(model, user);

        assertNotNull(bookedPhone.getBookedAt());
        assertEquals(user, bookedPhone.getBookedBy());
    }
}