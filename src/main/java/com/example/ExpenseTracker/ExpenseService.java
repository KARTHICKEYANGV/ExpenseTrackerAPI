package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseEntity createExpense(ExpenseEntity expense) {
        return expenseRepository.save(expense);
    }
}
