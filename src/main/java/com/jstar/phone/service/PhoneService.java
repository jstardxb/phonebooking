package com.jstar.phone.service;

import com.jstar.phone.entities.Phone;
import com.jstar.phone.exception.PhoneNotAvailableException;
import com.jstar.phone.exception.PhoneNotFoundException;
import com.jstar.phone.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PhoneService {

    private PhoneRepository phoneRepository;

    private NotificationService notificationService;

    public Phone bookPhone(String model, String user) {
        var phone = phoneRepository.findByModel(model)
                .orElseThrow(PhoneNotFoundException::instance);

        if (!phone.isAvailable()) {
            throw new PhoneNotAvailableException("Phone is already booked");
        }

        var mobilePhone = phoneRepository.save(Phone.builder()
                .id(phone.getId())
                .model(model)
                .bookedAt(LocalDateTime.now())
                .bookedBy(user)
                .build());

        notificationService.sendBookNotification(mobilePhone);

        return mobilePhone;
    }

    public Phone returnPhone(String model) {
        var phone = phoneRepository.findByModel(model)
                .orElseThrow(PhoneNotFoundException::instance);

        var mobilePhone = phoneRepository.save(Phone.builder()
                .id(phone.getId())
                .model(model)
                .bookedAt(null)
                .bookedBy(null)
                .build());

        notificationService.sendReturnNotification(mobilePhone);

        return mobilePhone;
    }
}