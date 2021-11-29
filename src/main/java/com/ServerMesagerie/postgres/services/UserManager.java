package com.ServerMesagerie.postgres.services;


import com.ServerMesagerie.dtos.UserDTO;
import com.ServerMesagerie.models.User;

import java.util.List;


public interface UserManager {

     User getUser(Long userId);

     User getFullUserByUsername(String username);

     List<User> getAllUsers();

     UserDTO save(User user);
}
