package com.ServerMesagerie.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conversationId", referencedColumnName = "conversation_id")
    public Conversation conversationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    public User senderUserId;

    @Column(name = "message")
    public String message;

    @Column(name = "time")
    public Long time;

    public Message(){}

    public Message(Long id, User senderUserId,Conversation conversationId, String message, Long time) {
        this.id = id;
        this.senderUserId = senderUserId;
        this.conversationId = conversationId;
        this.message = message;
        this.time = time;
    }
}
