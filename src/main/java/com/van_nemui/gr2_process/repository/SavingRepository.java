package com.van_nemui.gr2_process.repository;

import com.van_nemui.gr2_process.model.Loan;
import com.van_nemui.gr2_process.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SavingRepository extends JpaRepository<Saving, Long> {

    @Query("SELECT d FROM Saving d WHERE d.user_id = :user_id")
    List<Saving> findByUserId(@Param("user_id") Long user_id);

    @Query("SELECT d FROM Saving d WHERE d.user_id = :user_id and d.id = :id")
    Saving findById(@Param("user_id") Long user_id, @Param("id") Long id);

    @Query("SELECT t FROM Saving t WHERE t.user_id = :user_id and t.type = :type")
    List<Saving>findSavingByType(@Param("user_id") Long user_id, @Param("type") Long type);
}
