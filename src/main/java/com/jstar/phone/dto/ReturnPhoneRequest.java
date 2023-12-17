package com.jstar.phone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnPhoneRequest {

    @NotBlank(message = "Model cannot be blank")
    private String model;
}