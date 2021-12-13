package com.ServerMesagerie.dtos;


public class MessageDTO {
    public Long id;
    public String message;
    public String senderUsername;
    public Long senderUserId;
    public Long conversationId;
    public Long time;

    public MessageDTO(){}

    public MessageDTO(Long id, String message, String senderUsername, Long senderUserId, Long conversationId,
                      Long time) {
        this.id = id;
        this.message = message;
        this.senderUsername = senderUsername;
        this.senderUserId = senderUserId;
        this.conversationId = conversationId;
        this.time = time;
    }
}
