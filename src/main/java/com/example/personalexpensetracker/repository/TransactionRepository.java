package com.example.personalexpensetracker.repository;

import com.example.personalexpensetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
