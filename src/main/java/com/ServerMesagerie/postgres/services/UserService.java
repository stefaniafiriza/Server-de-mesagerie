package com.ServerMesagerie.postgres.services;

import com.ServerMesagerie.dtos.UserDTO;
import com.ServerMesagerie.models.Permission;
import com.ServerMesagerie.models.User;
import com.ServerMesagerie.postgres.repositories.UserRepository;
import com.ServerMesagerie.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserManager, UserDetailsService {

    private final UserRepository userRepository;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${password.salt}")
    private String salt;

    //TODO use an encoder for the password - maybe Hmac512

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void createAdminUser() {
        //TODO encode the password
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseGet(() -> optionalUser.orElseGet(() -> (User) Optional.empty().get()));
    }

    @Override
    public User getFullUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("User " + username + " was not found!");
        } else {
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO save(User user) {
        try {
            user.getPermissions().add(Permission.ROLE_USER);
            //TODO encode the password
            userRepository.save(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return UserUtils.userDTOMapper(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = userRepository.findByUsername(username);
        if (null != user) {
            return user;
        } else {
            throw new UsernameNotFoundException("User " + username + " was not found!");
        }
    }
}
