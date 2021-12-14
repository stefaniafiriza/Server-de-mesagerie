package com.ServerMesagerie.controllers;

import com.ServerMesagerie.dtos.AggregateDTO;
import com.ServerMesagerie.dtos.ConversationDTO;
import com.ServerMesagerie.dtos.MessageDTO;
import com.ServerMesagerie.dtos.UserDTO;
import com.ServerMesagerie.models.User;
import com.ServerMesagerie.postgres.services.ConversationManager;
import com.ServerMesagerie.postgres.services.UserManager;
import com.ServerMesagerie.server.AvailableUsers;
import com.ServerMesagerie.server.KafkaMessageProcessor;
import com.ServerMesagerie.server.KafkaMessageSenderProcessor;
import com.ServerMesagerie.utils.ConversationUtils;
import com.ServerMesagerie.utils.MessageUtils;
import com.ServerMesagerie.utils.UserUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messenger")
public class MessengerController {

    private final KafkaMessageSenderProcessor kafkaMessageSenderProcessor;
    private final KafkaMessageProcessor kafkaMessageProcessor;
    private final UserManager userManager;
    private final ConversationManager conversationManager;

    public MessengerController(KafkaMessageSenderProcessor kafkaMessageSenderProcessor,
                               KafkaMessageProcessor kafkaMessageProcessor,
                               UserManager userManager,
                               ConversationManager conversationManager) {
        this.kafkaMessageSenderProcessor = kafkaMessageSenderProcessor;
        this.kafkaMessageProcessor = kafkaMessageProcessor;
        this.userManager = userManager;
        this.conversationManager = conversationManager;
    }

    @RequestMapping("/send")
    public void produceMessage(@RequestBody MessageDTO message) {
        kafkaMessageSenderProcessor.startProducing(MessageUtils.messageMapper(message, userManager, conversationManager));
    }

    @GetMapping("/receive/{conversationId}")
    public List<MessageDTO> saveMessageToDBAndThenLoadMessageToUser(@PathVariable Long conversationId) {
        kafkaMessageProcessor.saveMessageToDB();
        return MessageUtils.messagesToDTOList(
                kafkaMessageProcessor.loadFromDB(conversationManager.getConversation(conversationId)));
    }

    @GetMapping("/getConversations/{userId}")
    public List<ConversationDTO> getConversations(@PathVariable Long userId) {
        return ConversationUtils.conversationsToDTOList(conversationManager.getConversations(userManager.getUser(userId)));
    }

    @GetMapping("/getConversationsAndUsers/{userId}")
    public AggregateDTO<List<UserDTO>, List<ConversationDTO>> getConversationsAndUsers(@PathVariable Long userId) {
        AggregateDTO<List<UserDTO>, List<ConversationDTO>> aggregateDTO = new AggregateDTO<>();
        AvailableUsers.getInstance().setAvailableUsers(userId);

        aggregateDTO.item1 = UserUtils.usersToDTOList(userManager.getAllUsers());
        aggregateDTO.item2 = ConversationUtils.conversationsToDTOList(conversationManager.getConversations(userManager.getUser(userId)));
        return aggregateDTO;

    }

    @RequestMapping("/createConversation")
    public ConversationDTO createConversation(@RequestBody ConversationDTO conversationDTO) {
        return ConversationUtils.conversationDTOMapper(conversationManager.createConversation(conversationDTO.users.stream().map(userManager::getUser).collect(Collectors.toList()), conversationDTO.name));
    }

    @RequestMapping("/updateConversation")
    public ConversationDTO updateConversation(@RequestBody ConversationDTO conversationDTO) {
        return ConversationUtils.conversationDTOMapper(conversationManager.updateConversation(conversationDTO.id, conversationDTO.users.stream().map(userManager::getUser).collect(Collectors.toList()), conversationDTO.name));
    }
}
