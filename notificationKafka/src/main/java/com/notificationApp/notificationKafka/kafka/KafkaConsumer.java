package com.notificationApp.notificationKafka.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationApp.notificationKafka.dto.Msg;

import com.notificationApp.notificationKafka.dto.UserEvent;
import com.notificationApp.notificationKafka.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @KafkaListener(topics = "course", groupId = "my_consumer")
    public void listen(String message) throws JsonProcessingException {
        UserEvent userEvent = objectMapper.readValue(message, UserEvent.class);
        System.out.println("Received message: " + userEvent.getEmail() + " " + userEvent.getEvent());
        emailNotificationService.notifyByMail(userEvent);
    }

}
