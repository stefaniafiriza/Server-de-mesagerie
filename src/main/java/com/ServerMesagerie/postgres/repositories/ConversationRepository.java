package com.ServerMesagerie.postgres.repositories;

import com.ServerMesagerie.models.Conversation;
import com.ServerMesagerie.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findAllByUsers(User user);
}
