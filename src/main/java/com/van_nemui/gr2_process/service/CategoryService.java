package com.van_nemui.gr2_process.service;

import com.van_nemui.gr2_process.model.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Map<Long, Category> getAllCategory();
    Category addNewCategory(Category category);
    int removeCategoryById(String username, Long id);

}
