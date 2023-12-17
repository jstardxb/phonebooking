package com.jstar.phone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPhoneRequest {

    private String model;

    private String bookedBy;
}