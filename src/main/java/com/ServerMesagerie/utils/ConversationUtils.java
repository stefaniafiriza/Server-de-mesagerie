package com.ServerMesagerie.utils;

import com.ServerMesagerie.dtos.ConversationDTO;
import com.ServerMesagerie.models.Conversation;
import com.ServerMesagerie.models.User;
import com.ServerMesagerie.postgres.services.ConversationManager;

import java.util.List;
import java.util.stream.Collectors;

public class ConversationUtils {

    public static ConversationDTO conversationDTOMapper(Conversation conversation){
        return new ConversationDTO(conversation.id, conversation.users.stream().map(User::getUserId).collect(Collectors.toList()), conversation.name);
    }

    public static Conversation conversationMapper(ConversationDTO conversationDTO, ConversationManager conversationManager){
        return conversationManager.getConversation(conversationDTO.id);
    }

    public static List<ConversationDTO> conversationsToDTOList(List<Conversation> conversations){
        return conversations.stream().map(ConversationUtils::conversationDTOMapper).collect(Collectors.toList());
    }

}
