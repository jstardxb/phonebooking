package com.jstar.phone.dto;

import com.jstar.phone.entities.Phone;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PhoneNotification {

    private Phone phone;
    private PhoneNotificationStatus status;
}