package com.ServerMesagerie.server;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class AvailableUsers {

    protected ConcurrentHashMap<Long, LocalDateTime> availableUsers = new ConcurrentHashMap<>();

    private static final class InstanceHolder {
        private static final AvailableUsers instance = new AvailableUsers();
    }

    public static AvailableUsers getInstance(){

        return InstanceHolder.instance;
    }

    private AvailableUsers(){}

    public void setAvailableUsers(Long id){
        availableUsers.put(id,LocalDateTime.now());
    }

    public void removeAvailableUsers(Long id){
        availableUsers.remove(id);
    }

    public boolean isAvailable(Long id){
        return availableUsers.containsKey(id);
    }
}
