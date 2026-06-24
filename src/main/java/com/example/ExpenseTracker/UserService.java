package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean findByEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) return true;

        return false;
    }

    public boolean checkByPassword(String email, String password){
        Optional<ExpenseUserEntity> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get().getPassword().equals(password);
        }
        return(false);
    }

}
