package com.jstar.phone.controller;

import com.jstar.phone.dto.BookPhoneRequest;
import com.jstar.phone.dto.ReturnPhoneRequest;
import com.jstar.phone.entities.Phone;
import com.jstar.phone.service.PhoneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phone")
@AllArgsConstructor
public class PhoneController {

    private PhoneService phoneService;

    @PostMapping("/book")
    public Phone bookPhone(@RequestBody @Valid BookPhoneRequest bookPhoneRequest) {
        return phoneService.bookPhone(bookPhoneRequest.getModel(), bookPhoneRequest.getBookedBy());
    }

    @PostMapping("/return")
    public Phone returnPhone(@RequestBody ReturnPhoneRequest returnPhoneRequest) {
        return phoneService.returnPhone(returnPhoneRequest.getModel());
    }
}