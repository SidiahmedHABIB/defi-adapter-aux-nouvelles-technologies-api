package net.sda.myresumeapi.services;

import net.sda.myresumeapi.entities.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    User updateUser(User user);
    User getUserById(String userId);
    Optional<User> findByEmail(String email);
    boolean checkCredentials(String email, String password);
}

