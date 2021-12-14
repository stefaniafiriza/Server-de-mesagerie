package com.ServerMesagerie.controllers;

import com.ServerMesagerie.dtos.UserDTO;
import com.ServerMesagerie.postgres.services.UserManager;
import com.ServerMesagerie.server.AvailableUsers;
import com.ServerMesagerie.utils.UserUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/get")
    private UserDTO getUser (@RequestParam Long userId) {
        return UserUtils.userDTOMapper(userManager.getUser(userId));
    }

    @GetMapping("/getByUsername")
    private UserDTO getUserByUsername (@RequestParam String username) {
        return UserUtils.userDTOMapper(userManager.getFullUserByUsername(username));
    }

    @GetMapping("/getAllUsers/{id}")
    private List<UserDTO> getAllUsers (@PathVariable ("id") Long userId) {
        AvailableUsers.getInstance().setAvailableUsers(userId);

        return UserUtils.usersToDTOList(userManager.getAllUsers());
    }

    @PostMapping("/save")
    private UserDTO save (@RequestBody UserDTO userDTO) {
        return userManager.save(UserUtils.userMapper(userDTO));
    }
}
