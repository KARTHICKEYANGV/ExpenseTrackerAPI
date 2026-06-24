package com.example.ExpenseTracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ExpenseUserEntity, Long> {
    Optional<ExpenseUserEntity> findByEmail(String email);
}
