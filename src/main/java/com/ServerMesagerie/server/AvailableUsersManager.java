package com.ServerMesagerie.server;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@Scope("application")
public class AvailableUsersManager implements Runnable {

    @SneakyThrows
    @Override
    public void run() {

        while(true){
            // check available users
            LocalDateTime now = LocalDateTime.now();
            List<Long> toRemove = new ArrayList<>();
            AvailableUsers.getInstance().availableUsers.forEach((id, time) -> {
                if (time.compareTo(now.minusSeconds(5)) < 0) {
                    toRemove.add(id);
                }
            });

            toRemove.forEach(AvailableUsers.getInstance()::removeAvailableUsers);

            Thread.sleep(3000);
        }

    }
}
