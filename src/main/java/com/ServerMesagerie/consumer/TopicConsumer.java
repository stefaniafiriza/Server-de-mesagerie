package com.ServerMesagerie.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicConsumer {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "myTopic", groupId = "kafka-sandbox")
    public void listen(@Payload Messages message) {
        System.out.println("Received Message: " + message);
        messages.add(message.getTextMessage());
    }

    public List<String> getMessages() {
        return messages;
    }
}
