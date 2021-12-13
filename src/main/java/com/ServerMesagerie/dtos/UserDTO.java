package com.ServerMesagerie.dtos;

import com.ServerMesagerie.models.Permission;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    public Long userId;

    public String username;

    public String password;

    public String firstName;

    public String lastName;

    public Set<Permission> permissions = new HashSet<>();

    public boolean available;

    public UserDTO(){}

    public UserDTO(Long userId, String username, String password, String firstName, String lastName, Set<Permission> permissions, boolean available) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permissions = permissions;
        this.available = available;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
