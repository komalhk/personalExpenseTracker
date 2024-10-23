package com.example.personalexpensetracker.controller;

import com.example.personalexpensetracker.model.Transaction;
import com.example.personalexpensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        return updatedTransaction != null ? ResponseEntity.ok(updatedTransaction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public String getSummary() {
        double totalIncome = transactionService.getTotalIncome();
        double totalExpenses = transactionService.getTotalExpenses();
        double balance = totalIncome - totalExpenses;

        return String.format("Total Income: %.2f, Total Expenses: %.2f, Balance: %.2f", totalIncome, totalExpenses,
                balance);
    }

    @GetMapping("/category/{category}")
    public List<Transaction> getTransactionsByCategory(@PathVariable String category) {
        return transactionService.getTransactionsByCategory(category);
    }

    @GetMapping("/date-range")
    public List<Transaction> getTransactionsByDateRange(@RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return transactionService.getTransactionsByDateRange(startDate, endDate);
    }

}
