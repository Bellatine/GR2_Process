package com.van_nemui.gr2_process.repository;

import com.van_nemui.gr2_process.model.Loan;
import com.van_nemui.gr2_process.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT d FROM Loan d WHERE d.user_id = :user_id")
    List<Loan> findByUserId(@Param("user_id") Long user_id);

    @Query("SELECT d FROM Loan d WHERE d.user_id = :user_id and d.id = :id")
    Loan findById(@Param("user_id") Long user_id, @Param("id") Long id);

    @Query("SELECT t FROM Loan t WHERE t.user_id = :user_id and t.type = :type")
    List<Loan>findLoanByType(@Param("user_id") Long user_id, @Param("type") Long type);

}
