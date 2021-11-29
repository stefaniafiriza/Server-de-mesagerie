package com.ServerMesagerie.dtos;


public class MessageDTO {
    public Long id;
    public String message;
    public String senderUsername;
    public Long senderUserId;
    public Long receiverUserId;
    public Long time;

    public MessageDTO(){}

    public MessageDTO(Long id, String message, String senderUsername, Long senderUserId, Long receiverUserId,
                      Long time) {
        this.id = id;
        this.message = message;
        this.senderUsername = senderUsername;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.time = time;
    }
}
