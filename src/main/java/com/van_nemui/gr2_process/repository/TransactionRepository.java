package com.van_nemui.gr2_process.repository;

import com.van_nemui.gr2_process.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAll();
    Optional<Transaction> findById(Long id);
    Optional<Transaction>findTransactionByType(Long type);
    void deleteById(Long id);

    @Query("SELECT d FROM Transaction d WHERE d.user_id = :user_id")
    List<Transaction> findByUserId(@Param("user_id") Long user_id);

    @Query("SELECT d FROM Transaction d WHERE d.user_id = :user_id and d.id = :id")
    List<Transaction> findById(@Param("user_id") Long user_id, @Param("id") Long id);

    @Query("SELECT d FROM Transaction d WHERE d.category_id = :category_id and d.user_id = :user_id")
    List<Transaction> findByCategoryId(@Param("user_id") Long user_id, @Param("category_id") Long category_id);

    @Query("SELECT t FROM Transaction t WHERE t.user_id = :user_id AND (t.trans_time BETWEEN :startOfDay AND :endOfDay)")
    List<Transaction> findAllByTransTime(@Param("user_id") Long user_id, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
