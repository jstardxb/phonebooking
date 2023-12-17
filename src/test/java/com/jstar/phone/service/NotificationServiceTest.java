package com.jstar.phone.service;

import com.jstar.phone.config.MessageConfig;
import com.jstar.phone.dto.PhoneNotification;
import com.jstar.phone.entities.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private MessageConfig messageConfig;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldSendBookNotification() {
        var phone = new Phone(1L, "Oneplus 9", LocalDateTime.now(), "User1");

        when(messageConfig.getInternalExchange())
                .thenReturn("exchange");
        when(messageConfig.getInternalNotificationRoutingKeys())
                .thenReturn("routingKey");

        notificationService.sendBookNotification(phone);

        verify(rabbitTemplate).convertAndSend(
                eq("exchange"),
                eq("routingKey"),
                any(PhoneNotification.class));
    }

    @Test
    void shouldSendReturnNotification() {
        var phone = new Phone(1L, "Oneplus 9", LocalDateTime.now(), "User1");

        when(messageConfig.getInternalExchange())
                .thenReturn("exchange");
        when(messageConfig.getInternalNotificationRoutingKeys())
                .thenReturn("routingKey");

        notificationService.sendReturnNotification(phone);

        verify(rabbitTemplate).convertAndSend(
                eq("exchange"),
                eq("routingKey"),
                any(PhoneNotification.class));
    }
}