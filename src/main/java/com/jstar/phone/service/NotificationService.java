package com.jstar.phone.service;

import com.jstar.phone.config.MessageConfig;
import com.jstar.phone.dto.PhoneNotification;
import com.jstar.phone.dto.PhoneNotificationStatus;
import com.jstar.phone.entities.Phone;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private MessageConfig messageConfig;
    private RabbitTemplate rabbitTemplate;

    public void sendBookNotification(Phone phone) {
        sendNotification(phone, PhoneNotificationStatus.BOOKED);
    }

    public void sendReturnNotification(Phone phone) {
        sendNotification(phone, PhoneNotificationStatus.RETURNED);
    }

    private void sendNotification(Phone phone, PhoneNotificationStatus status) {
        var notification = PhoneNotification.builder()
                .phone(phone)
                .status(status)
                .build();

        rabbitTemplate.convertAndSend(
                messageConfig.getInternalExchange(),
                messageConfig.getInternalNotificationRoutingKeys(),
                notification);
    }
}