package net.sda.myresumeapi.controllers;
import lombok.RequiredArgsConstructor;
import net.sda.myresumeapi.dto.LoginRequest;
import net.sda.myresumeapi.dto.LoginResponse;
import net.sda.myresumeapi.dto.RegisterRequest;
import net.sda.myresumeapi.entities.User;
import net.sda.myresumeapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterRequest registerRequest) {
        Map<String, String> response = new HashMap<>();

        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            response.put("message", "Email already exists");
            return ResponseEntity.status(409).body(response);
        }

        User user = new User();
        user.setFname(registerRequest.getFname());
        user.setLname(registerRequest.getLname());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());

        userService.registerUser(user);
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.checkCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            // Fetch user details if authentication is successful
            Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());
            User user = userOptional.get();
            return ResponseEntity.ok(new LoginResponse("Login successful", user.getId(), user.getFname(), user.getLname(),user.getScore()));
        } else {
            // Return an unauthorized status with a message for failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid credentials", null, null, null, 0));
        }
    }
}
