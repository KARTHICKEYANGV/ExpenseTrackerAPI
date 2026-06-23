package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<ExpenseUserEntity> createUser(@RequestBody String email, @RequestBody String password) {
        return new ResponseEntity<>(userService.createUser(email, password), HttpStatus.OK);
    }

}
