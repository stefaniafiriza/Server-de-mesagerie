package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.models.Conversation;
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

    public List<Message> loadAllMessagesForConversation(Conversation conversation) {
        return kafkaMessageRepository.findAllByConversationIdOrderById(conversation);
    }

    @Override
    public void saveKafkaMessageToDB(Message message) {
        kafkaMessageRepository.save(message);
    }
}
