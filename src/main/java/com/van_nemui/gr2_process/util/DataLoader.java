package com.van_nemui.gr2_process.util;

import com.van_nemui.gr2_process.model.Category;
import com.van_nemui.gr2_process.model.User;
import com.van_nemui.gr2_process.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.van_nemui.gr2_process.service.UserService;

import java.util.Map;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(DataLoader.class);

    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public DataLoader(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Start load data...");
        try {

            Map<String, User> userMap = userService.loadAllUser();
            Map<Long, Category> categoryMap = categoryService.getAllCategory();
            Cache.Users.ALLUSERS = userMap;
            Cache.Categories.ALL_CATEGORIES = categoryMap;
            if(userMap != null && categoryMap != null)
                logger.info("Load data success! " + userMap.size() + "-----" + categoryMap.size());
        }catch (Exception e){
            logger.error("Load data fail.", e);
        }


    }
}