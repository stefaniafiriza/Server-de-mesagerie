package com.ServerMesagerie.server;

import com.ServerMesagerie.models.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaMessageSenderProcessor {

    private final KafkaUtils kafkaUtils;

    KafkaProducer<String, Message> messageProducer;

    public KafkaMessageSenderProcessor(KafkaUtils kafkaUtils) {
        this.kafkaUtils = kafkaUtils;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        messageProducer.flush();
        messageProducer.close();
    }

    @PostConstruct
    private void createProducerOnStartUp() throws ExecutionException, InterruptedException {
        kafkaUtils.createTopicIfNotExist(kafkaUtils.messageTopicStorage,
                kafkaUtils.messageTopicStorageRetentionMS, kafkaUtils.defaultReplicaitonFactor);
        try {
            messageProducer = kafkaUtils.createKafkaProducer("all", StringSerializer.class, KafkaJsonSerializer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void startProducing(Message message) {
        try {
            messageProducer.send(new ProducerRecord<>(kafkaUtils.messageTopicStorage, message.receiverUserId.toString(), message));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
