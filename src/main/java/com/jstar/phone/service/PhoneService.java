package com.jstar.phone.service;

import com.jstar.phone.entities.Phone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PhoneService {

    public Phone bookPhone(String model, String user) {
        return new Phone(1L, "Nokia", LocalDateTime.now(), "User1");
    }
}