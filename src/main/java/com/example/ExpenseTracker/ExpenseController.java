package com.example.ExpenseTracker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    // Create expense
//    @PostMapping("/expenses")
//    public ResponseEntity<ExpenseEntity> createExpense(@RequestBody Map<String,Integer> expense) {
//        ExpenseEntity savedExpense = expenseService.createExpense(expense);
//        return ResponseEntity.ok(savedExpense);
//    }

}
