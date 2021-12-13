package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.models.Conversation;
import com.ServerMesagerie.postgres.repositories.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversationService implements ConversationManager {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Conversation getConversation(Long conversationId) {
        Optional<Conversation> optionalUser = conversationRepository.findById(conversationId);
        return optionalUser.orElseGet(() -> optionalUser.orElseGet(() -> (Conversation) Optional.empty().get()));
    }
}
