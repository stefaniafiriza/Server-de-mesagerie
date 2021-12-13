package com.ServerMesagerie.postgres.repositories;

import com.ServerMesagerie.models.Conversation;
import com.ServerMesagerie.models.Message;
import com.ServerMesagerie.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KafkaMessageRepository extends JpaRepository<Message, String> {
//    List<Message> findAllBySenderUserIdAndReceiverUserIdOrderById(User senderId, User receiverId);
    List<Message> findAllByConversationIdOrderById(Conversation conversation);
}
