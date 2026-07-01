package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseEntity createExpense(ExpenseEntity expense) {
        return expenseRepository.save(expense);
    }

    public List<ExpenseEntity> getAllExpenses(String email) {
        return expenseRepository.findByUserEmail(email);
    }

    public ExpenseEntity createExpenseForUser(String email, ExpenseEntity dto) {
        ExpenseUserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ExpenseEntity expense = new ExpenseEntity();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setCategory(dto.getCategory());
        expense.setDescription(dto.getDescription());
        expense.setUser(user);


        return expenseRepository.save(expense);
    }

    public void deleteExpense(String email, Long id) {
        ExpenseEntity expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You are not authorized to delete this expense");
        }
        expenseRepository.delete(expense);
    }

}
