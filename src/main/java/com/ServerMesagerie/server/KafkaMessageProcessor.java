package com.ServerMesagerie.server;

import com.ServerMesagerie.models.Message;
import com.ServerMesagerie.models.User;
import com.ServerMesagerie.postgres.services.KafkaServiceManager;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaMessageProcessor {

    public static final String GROUP_ID = "1";
    private final KafkaUtils kafkaUtils;

    private final KafkaServiceManager kafkaServiceManager;

    private Consumer<String, Message> consumer;

    public KafkaMessageProcessor(KafkaUtils kafkaUtils, KafkaServiceManager kafkaServiceManager) {
        this.kafkaUtils = kafkaUtils;
        this.kafkaServiceManager = kafkaServiceManager;
    }

    @PreDestroy
    public void onDestroy() {
        consumer.close();
    }

    @PostConstruct
    private void createKafkaConsumerOnStartup() throws ExecutionException, InterruptedException {
        kafkaUtils.createTopicIfNotExist(kafkaUtils.messageTopicStorage,
                kafkaUtils.messageTopicStorageRetentionMS, kafkaUtils.defaultReplicaitonFactor);

        consumer = kafkaUtils.createKafkaConsumer(GROUP_ID, new StringDeserializer(),
                new JsonDeserializer<>(Message.class));

        List<String> topics = new ArrayList<>();
        topics.add(kafkaUtils.messageTopicStorage);

        consumer.subscribe(topics);
    }

    public List<Message> loadFromDB(User senderId, User receiverId) {
        List<Message> conversationMessageList = new ArrayList<>();

        List<Message> receiverMessageList;
        List<Message> senderMessageList;

        try {
            receiverMessageList = kafkaServiceManager.loadAllMessagesForUser(receiverId, senderId);
            senderMessageList = kafkaServiceManager.loadAllMessagesForUser(senderId, receiverId);

            conversationMessageList.addAll(senderMessageList);
            conversationMessageList.addAll(receiverMessageList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (conversationMessageList.size() > 0) {
            conversationMessageList.sort(Comparator.comparing(kafkaMessage -> kafkaMessage.id,
                    Comparator.reverseOrder()));
        }

        return conversationMessageList;
    }

    public void saveMessageToDB() {
        synchronized (consumer) {
            ConsumerRecords<String, Message> consumerRecords = consumer.poll(Duration.ofMillis(5));

            if (consumerRecords.count() > 0) {
                consumerRecords.forEach(crv -> {
                    try {
                        kafkaServiceManager.saveKafkaMessageToDB(crv.value());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            }
        }
    }

}
