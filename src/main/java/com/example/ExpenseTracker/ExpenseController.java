package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

        List<ExpenseEntity> expenses = expenseService.getAllExpenses(email);
        return ResponseEntity.ok(expenses);

    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseEntity> createExpense(@RequestBody ExpenseEntity expense) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        ExpenseEntity createdExpense = expenseService.createExpenseForUser(email,expense);
        return ResponseEntity.ok(createdExpense);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        expenseService.deleteExpense(email,id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpenseEntity> updateExpense(@PathVariable Long id, @RequestBody ExpenseEntity expense) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        ExpenseEntity updatedExpense = expenseService.updateExpense(email, id, expense);
        return ResponseEntity.ok(updatedExpense);

    }

    @GetMapping("/filterByDate")
    public ResponseEntity<List<ExpenseEntity>> getAllExpensesDate(@RequestParam LocalDate startDate) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<ExpenseEntity> expenses = expenseService.getDateFilteredExpenses(email, startDate);
        return ResponseEntity.ok(expenses);

    }






}
