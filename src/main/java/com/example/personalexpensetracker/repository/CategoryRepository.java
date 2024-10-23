package com.example.personalexpensetracker.repository;

import com.example.personalexpensetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
