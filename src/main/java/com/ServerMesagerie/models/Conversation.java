package com.ServerMesagerie.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="conversation_id")
    public Long id;

    @Column(name="name")
    public String name;

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<User> users = new HashSet<>();

}
