package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.models.Conversation;

public interface ConversationManager {
    Conversation getConversation(Long conversationId);
}
