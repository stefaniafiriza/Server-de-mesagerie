package com.ServerMesagerie.dtos;

import java.util.List;

public class ConversationDTO {
    public Long id;

    public List<Long> users;

    public String name;

    public ConversationDTO(Long id, List<Long> users, String name) {
        this.id = id;
        this.users = users;
        this.name = name;
    }
}
