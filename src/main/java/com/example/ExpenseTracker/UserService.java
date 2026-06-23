package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public ExpenseUserEntity createUser(String email, String password) {
        ExpenseUserEntity user = new ExpenseUserEntity();
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);

    }
}
