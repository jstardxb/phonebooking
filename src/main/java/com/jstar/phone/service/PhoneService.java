package com.jstar.phone.service;

import com.jstar.phone.entities.Phone;
import com.jstar.phone.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PhoneService {

    private PhoneRepository phoneRepository;

    public Phone bookPhone(String model, String user) {
        var phone = phoneRepository.findByModel(model)
                .orElseThrow(()->new IllegalArgumentException("Phone not found"));

        return phoneRepository.save(Phone.builder()
                .id(phone.getId())
                .model(model)
                .bookedAt(LocalDateTime.now())
                .bookedBy(user)
                .build());
    }

    public Phone returnPhone(String model) {
        var phone = phoneRepository.findByModel(model)
                .orElseThrow(()->new IllegalArgumentException("Phone not found"));

        return phoneRepository.save(Phone.builder()
                .id(phone.getId())
                .model(model)
                .bookedAt(null)
                .bookedBy(null)
                .build());
    }
}