package com.ServerMesagerie.utils;

import com.ServerMesagerie.dtos.MessageDTO;
import com.ServerMesagerie.models.Message;
import com.ServerMesagerie.postgres.services.ConversationManager;
import com.ServerMesagerie.postgres.services.UserManager;

import java.util.List;
import java.util.stream.Collectors;

public class MessageUtils {

    public static MessageDTO messageDTOMapper(Message message){
        return new MessageDTO(message.id, message.message, message.senderUserId.username,
                message.senderUserId.userId, message.conversationId.id, message.time);
    }

    public static Message messageMapper(MessageDTO messageDTO, UserManager manager, ConversationManager conversationManager){
        return new Message(
                messageDTO.id,
                manager.getUser(messageDTO.senderUserId),
                conversationManager.getConversation(messageDTO.conversationId),
                messageDTO.message,
                messageDTO.time
        );
    }

    public static List<MessageDTO> messagesToDTOList(List<Message> messages){
        return messages.stream().map(MessageUtils::messageDTOMapper).collect(Collectors.toList());
    }

}
