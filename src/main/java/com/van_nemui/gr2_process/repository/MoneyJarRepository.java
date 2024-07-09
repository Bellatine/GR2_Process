package com.van_nemui.gr2_process.repository;

import com.van_nemui.gr2_process.model.MoneyJar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoneyJarRepository extends JpaRepository<MoneyJar, Long> {

    MoneyJar findMoneyJarById(Long id);
    @Query("SELECT m from MoneyJar m where m.user_id = :user_id")
    List<MoneyJar> findMoneyJarByUserId(@Param("user_id") Long user_id);

    @Query("SELECT m from MoneyJar m where m.user_id = :user_id and m.name = :name")
    MoneyJar findMoneyJarByName(@Param("user_id") Long user_id, @Param("name") String name);
}
