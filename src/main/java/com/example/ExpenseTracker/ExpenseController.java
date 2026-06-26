package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;


    @GetMapping("/getAll")
    public ResponseEntity<List<ExpenseEntity>> getAllExpenses() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<ExpenseEntity> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);

    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseEntity> createExpense(@RequestBody ExpenseEntity expense) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        expense.setUserEmail(email);
        ExpenseEntity createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.ok(createdExpense);
    }




}
