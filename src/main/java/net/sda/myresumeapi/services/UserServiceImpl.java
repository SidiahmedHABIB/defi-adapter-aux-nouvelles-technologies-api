package net.sda.myresumeapi.services;

import lombok.RequiredArgsConstructor;
import net.sda.myresumeapi.entities.User;
import net.sda.myresumeapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        user.setPassword(user.getPassword());  // Encrypt password
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkCredentials(String email, String password) {
        Optional<User> userOptional = findByEmail(email);
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password) ;
    }
}
