package com.ServerMesagerie.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="conversation_id")
    public Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<User> conversationUserId;

}
