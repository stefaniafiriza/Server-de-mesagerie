package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.models.Message;
import com.ServerMesagerie.models.User;

import java.util.List;

public interface KafkaServiceManager {

    void saveKafkaMessageToDB(Message message);

    List<Message> loadAllMessagesForUser(User senderId, User receiverId);
}
