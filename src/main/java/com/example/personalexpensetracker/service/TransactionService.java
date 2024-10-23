package com.example.personalexpensetracker.service;

import com.example.personalexpensetracker.model.Transaction;
import com.example.personalexpensetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Get transaction by ID
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    // Create a new transaction
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Update an existing transaction
    public Transaction updateTransaction(Long id, Transaction transaction) {
        if (transactionRepository.existsById(id)) {
            transaction.setId(id);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    // Delete a transaction
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // Get total income
    public double getTotalIncome() {
        return transactionRepository.findAll().stream()
                .filter(t -> "income".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Get total expenses
    public double getTotalExpenses() {
        return transactionRepository.findAll().stream()
                .filter(t -> "expense".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Get transactions by category
    public List<Transaction> getTransactionsByCategory(String category) {
        return transactionRepository.findAll().stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Get transactions within a date range
    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findAll().stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }
}
