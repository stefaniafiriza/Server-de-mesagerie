package com.ServerMesagerie.utils;

import com.ServerMesagerie.dtos.UserDTO;
import com.ServerMesagerie.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

    public static UserDTO userDTOMapper(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPermissions());
    }

    public static User userMapper(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPermissions());
    }

    public static List<UserDTO> usersToDTOList(List<User> users){
        return users.stream().map(UserUtils::userDTOMapper).collect(Collectors.toList());
    }

    public static boolean isAuthenticated(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
