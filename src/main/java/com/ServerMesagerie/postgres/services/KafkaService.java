package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.models.Message;
import com.ServerMesagerie.models.User;
import com.ServerMesagerie.postgres.repositories.KafkaMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaService implements KafkaServiceManager {

    private final KafkaMessageRepository kafkaMessageRepository;

    public KafkaService(KafkaMessageRepository kafkaMessageRepository) {
        this.kafkaMessageRepository = kafkaMessageRepository;
    }

    public List<Message> loadAllMessagesForUser(User senderId, User receiverId) {
        return kafkaMessageRepository.findAllBySenderUserIdAndReceiverUserIdOrderById(senderId, receiverId);
    }

    @Override
    public void saveKafkaMessageToDB(Message message) {
        kafkaMessageRepository.save(message);
    }
}
