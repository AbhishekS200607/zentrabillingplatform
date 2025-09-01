package com.zentra.api.repository;

import com.zentra.api.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.date >= :startDate AND e.date <= :endDate")
    BigDecimal getTotalExpensesBetween(LocalDateTime startDate, LocalDateTime endDate);
}