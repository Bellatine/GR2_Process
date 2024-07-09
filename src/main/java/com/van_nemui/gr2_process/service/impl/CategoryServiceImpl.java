package com.van_nemui.gr2_process.service.impl;

import com.van_nemui.gr2_process.model.Category;
import com.van_nemui.gr2_process.repository.CategoryRepository;
import com.van_nemui.gr2_process.service.CategoryService;
import com.van_nemui.gr2_process.util.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Map<Long, Category> getAllCategory() {
        try {
            List<Category> allCategory = categoryRepository.findAll();
            Map<Long, Category> categoryMap = new HashMap<>();
            if (allCategory == null || allCategory.isEmpty()) {
                logger.warn("Load categories fail");
                return null;
            }
            for (Category category : allCategory) {
                categoryMap.put(category.getId(), category);
            }
            return categoryMap;
        }catch (Exception e){
            logger.error("Get all categories fail! ", e);
            return null;
        }
    }

    @Override
    public Category addNewCategory(Category category) {
        try {
            if (category == null)
                return null;
            return categoryRepository.save(category);
        }catch (Exception e){
            logger.error("Add new category fail", e);
            return null;
        }
    }

    @Override
    public int removeCategoryById(String username, Long id) {
        try {
            if (Cache.Categories.ALL_CATEGORIES.get(id) == null) {
                logger.warn("Category not exist: " + id);
                return 0;
            }
            categoryRepository.deleteById(id);
            Cache.Categories.ALL_CATEGORIES.remove(id);
            return 1;
        }catch (Exception e){
            logger.error("remove category fail", e);
            return 0;
        }
    }
}
