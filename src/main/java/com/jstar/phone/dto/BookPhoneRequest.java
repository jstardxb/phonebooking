package com.jstar.phone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPhoneRequest {

    @NotBlank(message = "Model cannot be blank")
    private String model;

    @NotBlank(message = "Booked by cannot be blank")
    private String bookedBy;
}