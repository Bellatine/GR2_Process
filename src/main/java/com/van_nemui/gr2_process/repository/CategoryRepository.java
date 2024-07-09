package com.van_nemui.gr2_process.repository;

import com.van_nemui.gr2_process.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();

}
