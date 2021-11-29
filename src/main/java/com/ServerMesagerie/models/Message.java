package com.ServerMesagerie.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    public User senderUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    public User receiverUserId;

    @Column(name = "message")
    public String message;

    @Column(name = "time")
    public Long time;

    public Message(){}

    public Message(Long id, User senderUserId, User receiverUserId, String message, Long time) {
        this.id = id;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.message = message;
        this.time = time;
    }
}
