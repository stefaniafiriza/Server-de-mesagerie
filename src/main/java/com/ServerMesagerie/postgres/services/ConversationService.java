package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.models.Conversation;
import com.ServerMesagerie.models.User;
import com.ServerMesagerie.postgres.repositories.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService implements ConversationManager {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public List<Conversation> getConversations(User user) {
        return conversationRepository.findAllByUsers(user);
    }

    public Conversation createConversation(List<User> users, String name) {
        Conversation c = new Conversation();
        c.users.addAll(users);
        c.name = name;
        return conversationRepository.save(c);
    }


    @Override
    public Conversation updateConversation(Long id, List<User> users, String name) {
        Conversation c = new Conversation();
        c.id = id;
        c.users.addAll(users);
        c.name = name;
        return conversationRepository.save(c);
    }


    @Override
    public Conversation getConversation(Long conversationId) {
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        return optionalConversation.orElseGet(() -> optionalConversation.orElseGet(() -> (Conversation) Optional.empty().get()));
    }
}
