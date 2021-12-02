package com.ServerMesagerie.server;

import org.springframework.stereotype.Service;

@Service
public class KafkaStreamsProcessor {

    private final KafkaUtils kafkaUtils;

    public KafkaStreamsProcessor(KafkaUtils kafkaUtils) {
        this.kafkaUtils = kafkaUtils;
    }
}
