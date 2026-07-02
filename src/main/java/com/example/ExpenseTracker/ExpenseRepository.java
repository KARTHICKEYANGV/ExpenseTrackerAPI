package com.example.ExpenseTracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    List<ExpenseEntity> findByUserEmail(String email);

    List<ExpenseEntity> findByUserEmailAndDateGreaterThanEqual(String email, LocalDate startDate);

}
