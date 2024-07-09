package com.van_nemui.gr2_process.controller;

import com.van_nemui.gr2_process.model.Category;
import com.van_nemui.gr2_process.service.CategoryService;
import com.van_nemui.gr2_process.service.impl.CategoryServiceImpl;
import com.van_nemui.gr2_process.util.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gr2/category")
public class CategoryController {
    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService = new CategoryServiceImpl();

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllcategory(){
        List<Category> categoryList = (List<Category>) Cache.Categories.ALL_CATEGORIES.values();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/GetById")
    public ResponseEntity<?> getCategoryById(Long id){
        return ResponseEntity.ok(Cache.Categories.ALL_CATEGORIES.get(id));
    }

    @PostMapping("/addNewCategory")
    public ResponseEntity<?> addNewCategory(Category category){
        try{
            return ResponseEntity.ok(categoryService.addNewCategory(category));
        }catch (Exception e){
            logger.error("Add fail",e);
            return null;
        }
    }

    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable String userName, @PathVariable Long id){
        try{
            return ResponseEntity.ok(categoryService.removeCategoryById(userName, id));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
