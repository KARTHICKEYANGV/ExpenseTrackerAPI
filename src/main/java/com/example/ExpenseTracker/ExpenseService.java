package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;


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

    public ExpenseEntity updateExpense(String email, Long id, ExpenseEntity expense) {
        ExpenseEntity existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!existingExpense.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You are not authorized to update this expense");
        }
        existingExpense.setTitle(expense.getTitle());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setDate(expense.getDate());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setDescription(expense.getDescription());

        return expenseRepository.save(existingExpense);
    }

    public List<ExpenseEntity> getDateFilteredExpenses(String email, LocalDate startDate) {
        return expenseRepository.findByUserEmailAndDateGreaterThanEqual(email, startDate);
    }

}
