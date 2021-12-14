package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.models.Conversation;
import com.ServerMesagerie.models.User;

import java.util.List;

public interface ConversationManager {
    Conversation getConversation(Long conversationId);
    List<Conversation> getConversations(User user);
    Conversation createConversation(List<User> users, String name);
    Conversation updateConversation(Long id, List<User> users, String name);
}
