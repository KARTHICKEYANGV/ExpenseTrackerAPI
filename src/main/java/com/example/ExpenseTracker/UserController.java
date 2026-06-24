package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/users/register")
    public ResponseEntity<?> createUser(@RequestBody ExpenseUserEntity user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if(userService.findByEmail(email)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        userService.createUser(email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @GetMapping("/users/login")
    public ResponseEntity<?> loginUser(@RequestBody ExpenseUserEntity user){
        String email = user.getEmail();
        String password = user.getPassword();

        if(!userService.findByEmail(email)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email not found");
        }
        if(!userService.checkByPassword(email, password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok().body(Map.of("token",token));







    }



}
