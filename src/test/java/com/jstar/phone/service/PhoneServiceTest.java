package com.jstar.phone.service;

import com.jstar.phone.entities.Phone;
import com.jstar.phone.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneService phoneService;

    @Test
    void shouldBookPhoneSuccessfully() {
        var model = "Oneplus 9";
        var user = "User1";

        var phone = new Phone(1L, model, null, null);
        when(phoneRepository.findByModel(model))
                .thenReturn(Optional.of(phone));
        when(phoneRepository.save(any(Phone.class)))
                .thenAnswer(i -> i.getArgument(0));

        var bookedPhone = phoneService.bookPhone(model, user);

        assertNotNull(bookedPhone.getBookedAt());
        assertEquals(user, bookedPhone.getBookedBy());
    }
}